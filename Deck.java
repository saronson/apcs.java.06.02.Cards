/**
 * Deck represents 52 standard playing cards (no jokers) and allows you to use it to play games.
 * IMPORTANT: Do NOT add any more public methods to this class.
 */

public class Deck
{
    // You will manage this array of cards.
    private Card[] cards;
    // Add any other instance variables (if needed) here
    
    /**
     * Should create a standard deck of cards (no jokers)
     */
    public Deck() {
    }

    /**
     * return true if the deck has no cards left,
     * otherwise return false
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * return the number of cards left in the deck
     */
    public int numCards() {
        return 0;
    }

    /**
     * deal a card from the "top" of the deck
     * (you decide where the top is...)
     * If there are no cards left, return null
     */
    public Card dealCard() {
        return null;
    }

    /**
     * shuffle so that all cards still left
     * are rearranged in some random order
     */
    public void shuffle() {
    }

    /**
     *  add the given card back to the bottom of the deck
     */
    public void addCard(Card card) {
    }

    /** 
     * return a String of all existing cards in the deck
     *   each card separated by a comma and a space like:
     *   Ace of Spades, Ace of Clubs, Ace of Hearts ...
     * HINT:  Call the toString method for each card
     */
    public String toString() {
        return "";
    }

    /**
     * Returns true if all the cards in both decks
     * have the same values in the same positions.
     */
    public boolean equals(Object o)
    {
        Deck other = (Deck)o;

        return false;
    }
}
