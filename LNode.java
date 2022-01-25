/**Node container class to store objects*/
public class LNode {
    /**data stored in the container*/
    Student data;
    /**the pointer to the next object lining the list together*/
    LNode next;


    /**default constructor setting nulls */
    public LNode() {
        data = null ; next = null;
    }
    /**constructor with student object data to initialize*/
    public LNode(Student data) {
        this.data = data;
        this.next = null;
    }
    /**constructor to initialise and set the next pointer of this particular student data*/
    public LNode(Student data, LNode next) {
        this.data = data;
        this.next = next;
    }

//sets and gets

    public void setData(Student data) {
        this.data = data;
    }

    public void setNext(LNode next) {
        this.next = next;
    }

    public Student getData() {
        return this.data;
    }

    public LNode getNext() {
        return this.next;
    }


}
