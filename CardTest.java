import org.junit.*;
import java.lang.reflect.*;
/**
 * This class tests the Card class
 * 
 * @author Mr. Aronson
 */
public class CardTest extends junit.framework.TestCase
{
    String className = "Card";
    private  boolean failed = false;
    private  Class<?> c;
    private Constructor constructor;

    public static void main(String[] args)
    {
        CardTest ct = new CardTest ();
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
            constructor = c.getConstructor(new Class[] {int.class, int.class});
            t1 = constructor.newInstance(new Object[] {1, 2});
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

        checkField(t1, "int", "suit", 1, false);
        checkField(t1, "int", "rank", 2, false);

        String [] suits = {"Unknown", "Spades", "Hearts", "Diamonds", "Clubs"};
        String[] ranks = {"Unknown", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        try {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 13; j++) {
                    Object obj = constructor.newInstance(new Object[] {i, j});
                    callSet(obj, "setSuit", int.class, i);
                    if (!callGet(obj, "getSuit").equals(i))
                        failure("getSuit or setSuit not working properly");
                    if (!callGet(obj, "getSuitString").equals(suits[i]))
                        failure("getSuitString not working properly");
                    callSet(obj, "setRank", int.class, j);
                    if (!callGet(obj, "getRank").equals(j))
                        failure("getRank or setRank not working properly");
                    if (!callGet(obj, "getRankString").equals(ranks[j]))
                        failure("getRankString not working properly");
                    String str = ranks[j] + " of " + suits[i];
                    Object result = callGet(obj, "toString");
                    if (!str.equals(result))
                        failure("toString returns: " + result + " and should be " + str);
                }
            } 
        }catch (Exception e) {
            failure(e.toString());
        }

        testEquals();

        checkField(t1, "int", "ACE", 1, true);
        checkField(t1, "int", "JACK", 11, true);
        checkField(t1, "int", "QUEEN", 12, true);
        checkField(t1, "int", "KING", 13, true);
        checkField(t1, "int", "SPADES", 1, true);
        checkField(t1, "int", "HEARTS", 2, true);
        checkField(t1, "int", "DIAMONDS", 3, true);
        checkField(t1, "int", "CLUBS", 4, true);
    }

    private void testEquals()
    {

        try
        {
            Class<?> cl = Class.forName(className);
            constructor = cl.getConstructor(new Class[] {int.class, int.class});
            Object obj1 = constructor.newInstance(new Object[] {1, 1});
            Object obj2 = constructor.newInstance(new Object[] {1, 1});
            Method m = cl.getDeclaredMethod("equals", Object.class);
            Object result = m.invoke(obj1, obj2);
            if (!(Boolean)result) 
                failure("equals not working properly");
            callSet(obj2, "setSuit", int.class, 2);
            result = m.invoke(obj1, obj2);
            if ((Boolean)result) 
                failure("equals not working properly");
            callSet(obj2, "setSuit", int.class, 1);
            callSet(obj2, "setRank", int.class, 2);
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
