/**
 * Hand of cards used to play the game of Blackjack
 */
public class BlackjackHand {
  private Card card1;
  private Card card2;

  /** 
   * Construct a Blackjack hand out of two cards
   */
  public BlackjackHand(Card c1, Card c2) {

  }

  /**
   * return a String showing the hand. 
   * For example, "Ace of Clubs, Two of Spades"
   * IMPORTANT:  You should be calling the toString method of Card
   */
  public String toString() {
    return "";
  }

  /**
   * returns the hand's blackjack value
   * The cards 2-10 have their numerical values. 
   * The Jack, Queen and King are worth 10. 
   * The Ace can be worth 1 or 11.
   *   You would always pick 11 unless the hand is two Aces 
   *   then the second Ace would be worth 1 
   *   so as not to go over 21.
   */
  public int getBlackjackValue() {
    return 0;
  }
}