/**
 * A class that holds a number of cards at once.
 */
public class Hand {

    /**
     * creates a hand of the given number of cards
     * dealt from the given deck
     */
    public Hand(Deck deck, int numCards) {
    }

    /**
     * return a String of all the cards in the hand
     */
    public String toString() {
        return "";
    }

    /**
     * Put each card back into the deck 
     * by calling the deck's addCard method.
     */
    public void putCardsBack(Deck deck)
    {
    }

    /**
     * Returns true if all the cards are the same as another 
     * hand in the same position.
     */
    public boolean equals(Object o)
    {
        Hand other = (Hand)o;

        return false;
    }

}
