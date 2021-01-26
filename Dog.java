public class Dog
{
   private String name;
   private int age;
   
   public Dog(String theName, int theAge)
   {
       name = theName;
       age = theAge;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String theName)
    {
        name = theName;
    }
    
    public int getAge()
    {
        return age;
    }
    
    public void setAge(int theAge)
    {
        age = theAge;
    }
    
    public String toString()
    {
        return this.name;
    }
}

