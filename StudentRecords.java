import java.util.ArrayList;

/**
 * A class that extends the student class, this class is able to store classes for the student
 */
public class StudentRecords extends Student{

    /**
     * container for the courses
     */
    private ArrayList<String> listOfCourses;

    /**
     * Constructor for the student record
     * @param name name of student
     * @param ID id of the student
     */
    public StudentRecords(String name, String ID){
        super(name,ID);
        listOfCourses = new ArrayList<>();
    }

    /**
     * add the courses for the student
     * @param course course that needs to be added
     */
    public void add(String course){
        listOfCourses.add(course);
    }

    /**
     * removes the course from the students course list
     * @param course course to be removed
     */
    public void remove(String course){
        listOfCourses.remove(course);
    }


    /**
    *return a string with all the information of the student plus Courses if any
     * @return String representation of the student record
     * */
    @Override
    public String toString(){
        StringBuilder myStringBuilder = new StringBuilder("");
        myStringBuilder.append(super.toString());
        myStringBuilder.append("\nCourses: \n");
        for(String in: listOfCourses){
            myStringBuilder.append(in);
            myStringBuilder.append("\n");
        }
        return myStringBuilder.toString();
    }

}
