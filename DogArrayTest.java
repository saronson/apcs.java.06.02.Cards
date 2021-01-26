import org.junit.*;

/**
 * This class tests the Dog array
 * 
 * @author Mr. Aronson
 */
public class DogArrayTest extends junit.framework.TestCase
{
  
    public static void main(String args[])
    {
        DogArrayTest p = new DogArrayTest();
        p.testYoungestIndex();
        p.testYoungestName();
    }
    
    private Dog[] makeDogs()
    {
        Dog[] dogs = new Dog[5];
        dogs[0] = new Dog("Kiba", 5);
        dogs[1] = new Dog("Hudson", 2);
        dogs[2] = new Dog("Taylor", 6);
        dogs[3] = new Dog("Ripley", 1);
        dogs[4] = new Dog("Jack", 7);
        return dogs;
    }
    
    @Test
    public void testYoungestIndex()
    {
        Dog[] dogs = makeDogs();
        int index = DogArray.getYoungestIndex(dogs);
        assertEquals(3, index);
       
    }
    @Test
    public void testYoungestName()
    {
        Dog[] dogs = makeDogs();
        
        String name = DogArray.getYoungestName(dogs);
        assertEquals("Ripley", name);
    }

}
