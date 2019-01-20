
public class Card {
    public enum Color { // used enum to represent the group of named constants (colors)
        RED, GREEN, BLUE, YELLOW;
        public static Color random() {
            switch ((int)(Math.random()*4)) {
                case 0: return Color.RED;
                case 1: return Color.GREEN;
                case 2: return Color.BLUE;
                case 3: return Color.YELLOW;
            }
            return null;
        }
    }
    public enum Number {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, SKIP, REVERSE, PLUS2, WILD, PLUS4;
        public static Number random() {
            switch ((int)(Math.random()*15)) {
                case 0: return Number.ZERO;
                case 1: return Number.ONE;
                case 2: return Number.TWO;
                case 3: return Number.THREE;
                case 4: return Number.FOUR;
                case 5: return Number.FIVE;
                case 6: return Number.SIX;
                case 7: return Number.SEVEN;
                case 8: return Number.EIGHT;
                case 9: return Number.NINE;
                case 10: return Number.SKIP;
                case 11: return Number.REVERSE;
                case 12: return Number.PLUS2;
                case 13: return Number.WILD;
                case 14: return Number.PLUS4;
            }
            return null;
        }
    }


    Color color;
    Number number;

    public Card() {
        this(Color.random(), Number.random());
    }

    public Card(Color c, Number n) {
        this.color = c;
        this.number = n;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Card
            && ((Card)o).number == this.number
            && ((Card)o).color == this.color;
    }

    @Override
    public String toString() {
        return this.color + " " + this.number;
    }
}
