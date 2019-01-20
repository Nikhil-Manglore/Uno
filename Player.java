import java.util.*;

public class Player {
    ArrayList<Card> hand;
    String name;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<Card>();
		for (int i=0;i<5;i++) this.hand.add(new Card());
    }

    public void addCard(Card c) {
        this.hand.add(c);
    }

    ArrayList<Card> parseCards(String move) {
        ArrayList<Card> ret = new ArrayList<Card>();
        String[] cs = move.split(",");
        for (String c : cs) if (this.hand.size() > (int)Integer.valueOf(c)) ret.add(this.hand.get((int)Integer.valueOf(c)));
        return ret;
    }

    public String dispHand() {
        String str = " [0]: "+this.hand.get(0);
        for (int i=1;i<this.hand.size();i++) str += "\n ["+i+"]: "+this.hand.get(i);
        return str+"\n";
    }

    @Override
    public String toString() {
        return "Player "+this.name+"\n"+this.dispHand();
    }
}
