public class Hand {

   /**
    * creates a hand of the given number of cards
    * from the given deck of cards
    */
   public Hand(Deck deck, int numCards) {
   }
   
   /**
    * return a String of all the cards in the hand
    */
   public String toString() {
   }
   
   /**
    * returns the blackjack value of the hand
    * Each card is worth its face value
    * with the following exceptions:
    *
    * Jack, Queen and King are worth 10 each.
    *
    * The first Ace is usually worth 11.
    * Every other Ace is worth 1.
    * However, if the hand is a bust (over 21),
    * then the first Ace should be 1, too.
    */
   public int getBlackjackValue() {
      return 0;
   }
