import java.util.*;

public class Game {

	public static void main(String[] args) {
		new Game().run();
	}

	Scanner scan;
    Player[] players;
    Card current;
    int turn;
    boolean clockwise;
    int curPlayer;
    int accumulated2CardDebt;
    int accumulated4CardDebt;

    public Game() {
		scan = new Scanner(System.in);

		System.out.println("How many players?");
		int playerNum = Integer.valueOf(scan.nextLine());//0;
//		while (playerNum != 0) {
//			try {
//				playerNum = Integer.valueOf(scan.nextLine());
//			} catch (Exception e) {
//				System.out.println(x);
//			}
//		}
        this.players = new Player[playerNum];
		for (int i=0;i<playerNum;i++) {
			System.out.println("Player "+(i+1)+", what is your name?");
			this.players[i] = new Player(scan.nextLine());
		System.out.println("Created " + this.players[i]);
		}
    }

    public void run() {
        Player p;
        String move;

        current = new Card();
        clockwise = true;
        turn = 0;
        accumulated2CardDebt = 0;
        accumulated4CardDebt = 0;

        System.out.println("\n\n\nWelcome to Uno!");
        while (true) {
            // Player's turn
            p = this.players[curPlayer = turn % this.players.length];

            int prev2debt = this.accumulated2CardDebt;
            int prev4debt = this.accumulated4CardDebt;

            System.out.println(
				"The current player is " + p.name + " and the current card is " + current + ".\nWhat would you like to place?\n" +
                "[d]raw card, or enter a valid comma-separated combination of the cards in your hand (e.g. '1, 10, 4' or '7').\nHere is your hand:\n" + p.dispHand() +
                "\nCurrent Card: " + current);
            move = scan.nextLine();
            if (move.equals("draw") || move.equals("d")) p.addCard(this.drawCard());
            else if (! this.placeCards(p.parseCards(move)) ) {
                System.out.println("That's an invalid move!");
                continue;
            }

            if (this.accumulated2CardDebt != 0 && prev2debt == this.accumulated2CardDebt) {
                System.out.println("You collected "+this.accumulated2CardDebt*2+" cards.");
                for (int i=0;i<this.accumulated2CardDebt*2;i++) p.addCard(this.drawCard());
                this.accumulated2CardDebt = 0;
            }
            if (this.accumulated4CardDebt != 0 && prev4debt == this.accumulated4CardDebt) {
                System.out.println("You collected "+this.accumulated4CardDebt*4+" cards.");
                for (int i=0;i<this.accumulated2CardDebt*4;i++) p.addCard(this.drawCard());
                this.accumulated4CardDebt = 0;
            }


            if (p.hand.size() == 1)
            	System.out.println("Uno!!!");
            if (p.hand.size() == 0) {
                System.out.println("Player " + p + " wins!!!");
                return;
            }

            if(clockwise){
                turn += 1;
            } else {
                turn -=1;
            }
        }
    }

    // returns whether successful
    boolean placeCards(ArrayList<Card> toPlace) {
        if (toPlace.size() == 0)
        	return false;

        Card check = toPlace.get(0);
        if (check.number != current.number && check.color != current.color) return false;
        for (int i=1;i < toPlace.size();i++) if (toPlace.get(i).number != check.number) return false;
        Card placing = toPlace.get(toPlace.size() - 1);
        for (Card c : toPlace) this.players[this.curPlayer].hand.remove(c);

        // Special Cards
        switch (check.number) {
            case SKIP:
                this.turn++;
                break;
            case REVERSE:
                if (this.players.length == 2) {
                	if(clockwise){
                        turn += 1;
                    } else {
                        turn -=1;
                    }
                }
                this.clockwise = !this.clockwise;
                break;
            case PLUS2:
                this.accumulated2CardDebt++;
                break;
            case PLUS4:
                this.accumulated4CardDebt++;
            case WILD:
                System.out.println("Which color would you like to switch to?");
                switch (scan.nextLine().toUpperCase()) {
                    case "GREEN": placing.color = Card.Color.GREEN; break;
                    case "BLUE": placing.color = Card.Color.BLUE; break;
                    case "RED": placing.color = Card.Color.RED; break;
                    case "YELLOW": placing.color = Card.Color.YELLOW; break;
                    default:
                        System.out.println("Not a valid color!");
                        return this.placeCards(toPlace);
                }
                break;
            default:
                // Nothing
        }

        // Place last card down.
        this.current = placing;
        return true;
    }

    Card drawCard() {
        return new Card();
    }
}
