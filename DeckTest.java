import org.junit.*;
import java.lang.reflect.*;
/**
 * This class tests the Deck class
 * 
 * @author Mr. Aronson
 */
public class DeckTest extends junit.framework.TestCase
{
    String className = "Deck";
    private  boolean failed = false;
    private  Class<?> c;
    private Constructor constructor;

    public static void main(String[] args)
    {
        DeckTest ct = new DeckTest ();
        ct.test();
    }

    public void checkField(Object t1, String fieldType, String fieldName, Object value, boolean isFinal) {
        try {
            Field field = c.getDeclaredField(fieldName);
            String temp = field.toString();
            //       System.out.println(temp);

            if (!fieldType.equals(field.getType().toString())) 
                failure(fieldName + " is not type " + fieldType);
            if (!isFinal && !temp.contains("private"))
                failure("instance variable " + fieldName + " should be private");
            if (isFinal && (!temp.contains("final") || !temp.contains("static") || !temp.contains("public"))) 
                failure("constants like " + fieldName + " should be public, static and final");

            if (isFinal && !temp.contains("final")) 
                failure(fieldName + " is not final");

            field.setAccessible(true);
            Object val = field.getInt(t1);
            if (!val.equals(value)) {
                failure(fieldName + " has an incorrect value");
            }

        } catch (NoSuchFieldException e) {
            failure("Missing " + fieldType + " " + fieldName);
        } catch (Exception e) {
            failure(e.toString());
        }
    }

    @Test
    public void test() {
        Object t1 = null;
        try
        {
            c = Class.forName(className);
            constructor = c.getConstructor(new Class[] {});
            t1 = constructor.newInstance();
        }
        catch (NoClassDefFoundError e)
        {
            failure("Epic Failure: missing Card class");
        }
        catch (ClassNotFoundException e)
        {
            failure("Epic Failure: missing Card class");
        }
        catch (NoSuchMethodException e)
        {
            failure("missing constructor Card(int suit, int rank)");
        }
        catch (Exception e) {
            failure(e.toString());
        }

        try {
            Object[] tempCards = new Object[52];
            if (!callGet(t1, "numCards").equals(52))
                failure("numCards not working properly");
            if (!callGet(t1, "isEmpty").equals(false))
                failure("isEmpty not working properly");  
            System.out.println(t1);
            for (int i = 51; i >= 0; i--) {
                Object obj = callGet(t1, "dealCard");
                tempCards[51-i] = obj;

                if (!callGet(t1, "numCards").equals(i))
                    failure("dealCard and/or numCards not working properly");   
            }
            if (!callGet(t1, "isEmpty").equals(true))
                failure("isEmpty not working properly"); 
            for (int i = 0; i < tempCards.length; i++) {
                callSet(t1, "addCard", Card.class, tempCards[i]);
                if (!callGet(t1, "isEmpty").equals(false))
                    failure("isEmpty not working properly"); 

                if (!callGet(t1, "numCards").equals(i+1))
                    failure("dealCard and/or numCards not working properly");   
            }
            String answer = "Ace of Spades, Ace of Hearts, Ace of Diamonds, Ace of Clubs, 2 of Spades, 2 of Hearts, 2 of Diamonds, 2 of Clubs, 3 of Spades, 3 of Hearts, 3 of Diamonds, 3 of Clubs, 4 of Spades, 4 of Hearts, 4 of Diamonds, 4 of Clubs, 5 of Spades, 5 of Hearts, 5 of Diamonds, 5 of Clubs, 6 of Spades, 6 of Hearts, 6 of Diamonds, 6 of Clubs, 7 of Spades, 7 of Hearts, 7 of Diamonds, 7 of Clubs, 8 of Spades, 8 of Hearts, 8 of Diamonds, 8 of Clubs, 9 of Spades, 9 of Hearts, 9 of Diamonds, 9 of Clubs, 10 of Spades, 10 of Hearts, 10 of Diamonds, 10 of Clubs, Jack of Spades, Jack of Hearts, Jack of Diamonds, Jack of Clubs, Queen of Spades, Queen of Hearts, Queen of Diamonds, Queen of Clubs, King of Spades, King of Hearts, King of Diamonds, King of Clubs";
            Object str = callGet(t1, "toString");
            System.out.println(str);
            if (!str.equals(answer)) {
                failure("toString is getting\n   " + str + "\n but should be\n   " + answer);
            }
        }catch (Exception e) {
            failure(e.toString());
        }

        testEquals();
    }
    private void testEquals()
    {

        try
        {
            Class<?> cl = Class.forName(className);
            constructor = cl.getConstructor();
            Object obj1 = constructor.newInstance();
            Object obj2 = constructor.newInstance();
            Method m = cl.getDeclaredMethod("equals", Object.class);
            Object result = m.invoke(obj1, obj2);
            if (!(Boolean)result) 
                failure("equals not working properly");
            Object temp = callGet(obj1, "dealCard");
            result = m.invoke(obj1, obj2);
            if ((Boolean)result) 
                failure("equals not working properly");

        }
        catch (NoSuchMethodException e)
        {
            failure("missing equals");
        }
        catch(Exception e) {
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
