/**
 * A Queue class that uses the student linked list class to structure the queue
 */

public class StudentQueue {
    /**
     * that will be the queue
     */
    private LinkedStudentList queue;

    /**
     * Constructor init linked list
     */
    public StudentQueue(){
        queue = new LinkedStudentList();
    }

    /**
     * queues the students, adds in no order
     * @param student student to be added to the queue
     * Runtime-O(1)
     */
    public void enQue(Student student){
        queue.insert(student);
    }

    /**
     * takes the front of the line student
     * @return student at the front of the line
     * Runtime- O(1)
     */
    public Student deQue(){
        Student stu = queue.front();
        queue.remove(stu);
        return stu;
    }

    /**
     * method to check if there is anybody on the list
     * @return true or fals
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     * returns the size of the queue, meaning how many are waiting
     * @return the size of the queue
     */
    public int size(){
        return queue.size();
    }

}
