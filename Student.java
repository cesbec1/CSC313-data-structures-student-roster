/**Student class, that will hold a students information*/
public class Student implements Comparable<Student>{
    private String firstName;
    private String lastName;
    private String ID;

    /**
    constructor for student, provided the name and id or just name with no id 
     */
    public Student(String firstName, String lastName, String ID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = lastName.charAt(0)+ID;
    }
    
    public Student(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = null;
    }

  
	/**returns name as single string*/
    public String getName(){
        return String.format("%s %s", firstName, lastName);
    }

    /**returns only first name*/
    public String getFirstName(){
        return firstName;
    }

    /**returns only last name*/
    public String getLastName(){
        return lastName;
    }


    /**returns the ID*/
    public String getID(){
        return ID;
    }

    /**set first name*/
    public void setFirstName(String newFirstName){
        firstName = newFirstName;
    }

    /**set last name*/
    public void setLastName(String newLastName){
        lastName = newLastName;
    }

    /**set ID assuming only the numbers will change*/
    public void setID(String newID){
        ID = getLastName().charAt(0) + newID;
    }

    /**
    checks if same object, if empty or not same class
    use string equals to check if same or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Student student = (Student) o;
        return ID.equals(student.ID);
    }

    /**returns string representation of student*/
    @Override
    public String toString(){
        return String.format("Name:%s %s, ID:%s", firstName,lastName, ID);
    }

    /**
    compares lastname then first and ID if necessary
    -number if this comes before that
    +number if that comes before this
    0 if they are equal in both first and last name and ID
    student implements comparable, it is enough to know the above
    else I could use the longer code to return -1,1 or 0 but I think whats not commented is easier
    to understand, tested both and both work good
     */
    @Override
    public int compareTo(Student o) {
        if(lastName.compareToIgnoreCase(o.lastName)==0){
           if (firstName.compareToIgnoreCase(o.firstName)==0){
               return this.getID().compareToIgnoreCase(o.getID());
           }
            return firstName.compareToIgnoreCase(o.firstName);
        }
        return lastName.compareToIgnoreCase(o.lastName);
       }
}

