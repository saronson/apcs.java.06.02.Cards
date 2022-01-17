/**
 * In Blackjack, you will deal hands of two cards each.
 * Adding up each hand's points, the closest to 21 
 * without going over is the winner.
 */
public class Main 
{
    private BlackjackHand[] hands;

    public static void main(String args[])
    {
        Main main = new Main();
        main.playBlackjack(4);
    }

    /**
     * Write the game of blackjack for the 
     *    given number of players.
     * 1) create a deck and shuffle it
     * 2) fill the hands array with the properly created 
     *    number of BlackjackHand objects
     * 3) print the hands and their values by calling 
    *     toString and getBlackjackValue for each hand
     * 4) print who won. If a tie, print all who tied
     * 5) return the index of the hand that won, -1 if a tie  
     */
    public int playBlackjack(int numPlayers)
    {
       return -1;
    }

    /**
     * DO NOT USE THIS OR MODIFY THIS
     */
    public BlackjackHand[] getBlackjackHands() {
      return hands;
    }

}