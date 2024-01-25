/**
 * Create a well-designed Card class with:
 * - int instance variables for suit and rank
 *     Suits: Spades (1), Hearts (2), Diamonds (3), Clubs (4)
 *     Ranks: Ace (1), 2-10, Jack (11), Queen (12), King (13)
 * - constants (especially for the special values described right above here)
 *   i.e. there should be no "magic numbers" in your code
 * - constructor that takes suit and rank parameters (in that order)
 * - accessors (getters) getSuit and getRank
 * - mutators (setters) setSuit and setRank
 * - public String getSuitString()
 *     return a String for the suit like Spades, Hearts, etc.
 * - public String getRankString()
 *     return a String for the rank like 4, 6, Jack, Queen, King, Ace, etc.
 * - public String toString()
 *     formatted exactly like these examples: 
 *     "9 of Spades", "Ace of Hearts", "King of Clubs"
 * - public equals(Object other) (finish the given method)
 *     true if two cards have the same suits and ranks
 */
public class Card
{
    // add instance variables here

    // add suit constants here
    public static final int SPADES = 1; // this is an example constant for the suits

    // add rank constants here
    public static final int JACK = 11; // this is an example constant for the ranks


   /**
    * return true if this.suit and this.rank are the same as other.suit and other.rank respectively, otherwise return false.
    */
   public boolean equals(Object obj) {
      Card other = (Card)obj;
      
      return false;
   }
}
