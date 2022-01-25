/**
 * implementation of linked list using students as data in the nodes
 */

public class LinkedStudentList {
    /**Node container containing the data and a pointer to the next object*/

    private LNode head;

    /**Constructor that sets the head as null as our entry point to the list*/
    public LinkedStudentList() {
        head = new LNode();
    }

    /**
     * inserts students based on Students compareTo method, it is done lexicographically by their last name
     * @param student - student to be inserted into the list
     * Runtime - O(n)  best case O(1)
     */
    public void insertSorted(Student student){
        LNode current = head.next;
        LNode previous= head;
        while(current!=null && current.data.compareTo(student)<1){
            previous = current;
            current = current.next;
        }
        previous.next = new LNode(student,current);
    }
    /** checks whether the list is empty or not*/
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * this is a general insert method, not order in any way
     * @param student- student to be inserted into the list
     * Runtime- O(n) best case O(1)
     * */
    public void insert(Student student){
        LNode current = head.next;
        LNode previous = head;
        while(current!=null){
            previous = current;
            current = previous.next;
        }
        previous.next = new LNode(student);
    }

    /** A method that returns a string representation of the list
     * @return the string representation
     * Runtime- O(n)
     */
    @Override
    public String toString(){
        StringBuilder myStringBuilder = new StringBuilder("");
        LNode current = head.next;
        LNode previous= head;
        while(current!=null){
            myStringBuilder.append(current.data + "\n");
            previous = current;
            current = current.next;
        }
        return myStringBuilder.toString();
    }

    /**
     * A method that returns the size of the list
      * @return an integer representing the amount of objects in the list
     * Runtime- O(n)
     */
    public int size() {
        int counter=0;
        LNode current = head.next;
        LNode previous = head;
        while (current != null) {
            previous = current;
            current = current.next;
            counter++;
        }
        return counter;
    }

    /**
     * Method for removing the student from the list
     * @param student- student to be removed
     * Runtime- O(n) best case O(1)
     */
    public void remove(Student student) {
        LNode current = head.next;
        LNode previous = head;
        while (current != null && current.data.compareTo(student) != 0) {
            previous = current;
            current = current.next;
        }
        if (current != null) {
            if (current.data.compareTo(student) == 0) {
                previous.next = current.next;
            }
        }else {
            System.out.println("not found");
        }
    }

    /** Method that returns an array of the nodes data. in this case a Student array
     * @return the student array which is the final roster
     * Runtime- O(n)
     */
    public Student[] toArray(){
        LNode current= head.next;
        LNode previous = head;
        Student[] arr = new Student[size()];
        int counter = 0;
        while(current!=null){
            arr[counter] = current.data;
            previous = current;
            current = current.next;
            counter++;
        }
        return arr;
    }

    /**
     *  A method used for searching by the students id
     * @param studentId - it is a String in the form of a letter followed by 6 digits
     * @return if found it returns the student that is being searched else null meaning not found
     * Runtime- O(n)
     */
    public Student searchByID(String studentId) {
        LNode current = head.next;
        Student stu = null;
        while(current!=null ){
            if(current.data.getID().equals(studentId)){
                stu = current.data;
                return stu;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Method to search the list by student name
     * @param name - String parameter representing the student name
     * @return- the student that was searched for or null if not found
     * Runtime- O(n)
     */
    public Student searchByName(String name) {
        LNode current = head.next;
        Student stu = null;
        while (current != null) {
            if (current.data.getName().compareTo(name) == 0) {
                stu = current.data;
                return stu;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Method that returns the front of the list
     * @return the front of the list
     * Runtime- O(1)
     */
    public Student front(){
        LNode current = head.next;
        return current.data;
    }

}






