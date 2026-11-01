import java.util.Objects;

class Card {
    private final String rank;
    private final String suit;
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return Objects.equals(rank, card.rank) && Objects.equals(suit, card.suit);
    }
    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

class Drive {
    public static void main(String[] args) {
        Card[] incomingCards = new Card[] {
                new Card("Ace", "Spades"),
                new Card("King", "Hearts"),
                new Card("Queen", "Diamonds"),
                new Card("Ace", "Spades"), // First duplicate
                new Card("Jack", "Clubs")
        };

        Card[] deck = new Card[incomingCards.length];
        int cardCount = 0;
        for (Card nextCard : incomingCards) {
            boolean isDuplicate = false;

            for (int i = 0; i < cardCount; i++) {
                if (deck[i].equals(nextCard)) {
                    isDuplicate = true;
                    System.out.println("Duplicate found: " + nextCard);
                    break;
                }
            }

            if (!isDuplicate) {
                deck[cardCount] = nextCard;
                cardCount++;
            }
        }
    }
}