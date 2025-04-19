package studentapp;


public class Student {
    private int prn;
    private String name;
    private String dob;      // in format YYYY-MM-DD
    private float marks;     

    public Student(int prn, String name, String dob, float marks) {
        this.prn = prn;
        this.name = name;
        this.dob = dob;
        this.marks = marks;
    }

    // Getters and setters
    public int getPrn() { return prn; }
    public void setPrn(int prn) { this.prn = prn; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public float getMarks() { return marks; }
    public void setMarks(float marks) { this.marks = marks; }

    @Override
    public String toString() {
        return String.format("PRN: %d | Name: %s | DoB: %s | Marks: %.2f",
                             prn, name, dob, marks);
    }
}
