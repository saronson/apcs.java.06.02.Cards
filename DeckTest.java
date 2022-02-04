import org.junit.*;
import java.lang.reflect.*;
import java.util.*;
/**
 * This class tests the Deck class
 * 
 * @author Mr. Aronson
 */
public class DeckTest extends junit.framework.TestCase
{

    private  Class<?> c;
    private Constructor constructor;

    public static void main(String[] args)
    {
        DeckTest ct = new DeckTest ();
        ct.testDeck();
        ct.testHand();
        ct.testGame();
    }

    @Test
    public void testDeck() {
        Deck deck = new Deck();
        Card[] tempCards = new Card[52];
        if (deck.numCards() != 52)
            failure("numCards not working properly");
        if (deck.isEmpty())
            failure("isEmpty not working properly");  
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (int i = 51; i >= 0; i--) {
            Card obj = deck.dealCard();
            tempCards[51-i] = obj;

            if (deck.numCards() != i)
                failure("dealCard and/or numCards not working properly"); 
            String str = obj.toString();
            if (map.get(str) != null)
                System.out.println(str + " already exists");
            else
                map.put(str, 1);

        }
        if (deck.numCards() != 0)
            failure("dealCard and/or numCards not working properly"); 

        if (!deck.isEmpty())
            failure("isEmpty not working properly"); 
        for (int i = 0; i < tempCards.length; i++) {
            deck.addCard(tempCards[i]);
            if (deck.isEmpty())
                failure("isEmpty not working properly"); 

            if (deck.numCards() != i+1)
                failure("dealCard and/or numCards not working properly");   
        }
        deck = new Deck();

        String answer = "Ace of Spades, Ace of Hearts, Ace of Diamonds, Ace of Clubs, 2 of Spades, 2 of Hearts, 2 of Diamonds, 2 of Clubs, 3 of Spades, 3 of Hearts, 3 of Diamonds, 3 of Clubs, 4 of Spades, 4 of Hearts, 4 of Diamonds, 4 of Clubs, 5 of Spades, 5 of Hearts, 5 of Diamonds, 5 of Clubs, 6 of Spades, 6 of Hearts, 6 of Diamonds, 6 of Clubs, 7 of Spades, 7 of Hearts, 7 of Diamonds, 7 of Clubs, 8 of Spades, 8 of Hearts, 8 of Diamonds, 8 of Clubs, 9 of Spades, 9 of Hearts, 9 of Diamonds, 9 of Clubs, 10 of Spades, 10 of Hearts, 10 of Diamonds, 10 of Clubs, Jack of Spades, Jack of Hearts, Jack of Diamonds, Jack of Clubs, Queen of Spades, Queen of Hearts, Queen of Diamonds, Queen of Clubs, King of Spades, King of Hearts, King of Diamonds, King of Clubs";
        String str = deck.toString();

        if (!str.equals(answer) && !str.substring(0, str.length()-2).equals(answer)) {
            failure("toString is getting\n   " + str + "\n but should be\n   " + answer);
        }
        deck.shuffle();

        for (int i = 0; i < 52; i++) {
            Card obj = deck.dealCard();

            if (deck.numCards() != 51-i)
                failure("shuffle not working properly"); 
            String str2 = obj.toString();
            if (map.get(str2) == null)
                failure(str2 + " does not exist (or is there multiple times) after shuffle");
            else
                map.remove(str2);

        }
        if (map.size() != 0)
            failure("Not all cards in deck after shuffle.");


        Deck obj1 = new Deck();
        Deck obj2 = new Deck();

        boolean result = obj1.equals(obj2);
        if (!result) 
            failure("equals not working properly (should be public boolean equals(Object o))");
        Card temp = obj1.dealCard();
        result = obj1.equals(obj2);
        if (result) 
            failure("equals not working properly (should be public boolean equals(Object o))(did you assume both decks have the same number of cards?)");

    }

    @Test
    public void testHand() {

        boolean foundAceAce = false;
        for (int j = 0; j < 1000; j++) {

            Deck deck = new Deck();
            deck.shuffle();

            int sum = 0;
            for (int i = 0; i < 26; i++)
            {

                Card c1 = deck.dealCard();
                Card c2 = deck.dealCard();
                BlackjackHand h = new BlackjackHand(c1, c2);

                int num = h.getBlackjackValue();

                sum += num;

            }

            if (sum == 370 || sum == 360) {
                foundAceAce = true;
            } else if (sum != 380) {
                failure("getBlackjackValue has a problem. Are you doing Ace and face cards properly?");
                break;
            }

        }
        if (!foundAceAce)
            failure("Ace ace not calculated properly in getBlackjackValue");

    }

    @Test
    public void testGame() {

        try
        {
            /*
            Class<?> main = Class.forName("Main");
            Constructor cMain = main.getConstructor();
            Object game = cMain.newInstance();
            Method m = main.getDeclaredMethod("playBlackjack");
             */
            Main game = new Main();
            for (int numPlayers = 2; numPlayers < 10; numPlayers++) {
                int[] winners = new int[numPlayers+1];
                for (int k = 0; k < 1000; k++) {
                    //       Object result = m.invoke(game, j);
                    int result = game.playBlackjack(numPlayers);
                    BlackjackHand[] hands = game.getBlackjackHands();
                    assertTrue("should create hands", hands != null);
                    assertEquals("Should have " + numPlayers + " players", numPlayers, hands.length);
                    int winner = 0;

                    for (int i = 1; i < hands.length; i++) {
                        assertTrue("hand should not be null", hands[i] != null);
                        if (hands[i].getBlackjackValue() > hands[winner].getBlackjackValue())
                            winner = i;
                        else if (hands[i].getBlackjackValue() == hands[winner].getBlackjackValue()) {
                            winner = -1;
                            break;
                        }
                    }
                    assertEquals("wrong winner/tie", winner, result);
                    if (winner == -1)
                      winner = hands.length;
                    winners[winner] += 1;
                }
                for (int i = 0; i < winners.length; i++) {
                    assertTrue("imbalance in winners, was deck shuffled? " + i + " " + winners[i], winners[i]>0);
                }


                //      System.out.println(result);
            }

        }catch (Exception e) {
            failure(e.toString());
        }

    }

    private void failure(String str)
    {
        //    System.out.println("*** Failed: " + str);
        fail(str);
    }

    public Object callGet(Object t, String get)
    {
        try
        {
            Method m = c.getDeclaredMethod(get);
            Object temp = m.invoke(t);
            return temp;
        }
        catch (NoSuchMethodException e)
        {
            failure("missing " + get);

        }
        catch(Exception e) {
            failure(e.toString());
        }
        return null;
    }

    public void callSet(Object t, String setMethod, Class cl, Object setValue)
    {
        try
        {
            Method m = c.getDeclaredMethod(setMethod, cl);
            m.invoke(t, setValue);
        }
        catch (NoSuchMethodException e)
        {
            failure("missing " + setMethod);
        }
        catch(Exception e) {
            failure(e.toString());
        }

    }

}
