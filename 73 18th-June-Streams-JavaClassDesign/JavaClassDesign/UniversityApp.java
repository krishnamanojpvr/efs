// Design a Java application to simulate a university’s student course registration 
// system. 

// This system should:
//  - Register Undergraduate and Postgraduate students
//  - Enforce course registration limits based on student type
//  - Throw exceptions when course limits are exceeded
//  - Sort and display student records
//  - Demonstrate key OOP principles such as inheritance, abstract classes, interfaces, 
//  - custom exceptions, encapsulation, and comparators

// Implement the following:
// --------------------------
// ✅ Interface: Printable
// 	Method: void printDetails()

// ✅ Class: Course
// 	Fields: code, title, credits
// 	Constructor and toString() → returns "code - title"

// ✅ Abstract Class: Student implements Printable
// 	Fields: name, id, List<Course>
// 	Abstract Method: int getMaxCourses()
// 	Method: registerCourse(Course) → throws CourseLimitExceededException 
// 	              if the limit is exceeded
// 	toString() → returns "name (id)"
// 	printDetails() → prints student info and registered courses

// ✅ Class: Undergraduate extends Student
// 	Method: 
// 		- getMaxCourses() returns 3

// ✅ Class: Postgraduate extends Student
// 	Method: 
// 		- getMaxCourses() returns 2

// ✅ Class: CourseLimitExceededException extends Exception
// 	Custom exception with a message

// ✅ Class: StudentNameComparator implements Comparator<Student>
// 	Sorts students alphabetically by name (case-insensitive)

// ✅ Class: University implements Printable
// 	Fields: name, List<Student>
// 	Methods:
// 		- addStudent(Student)
// 		- printAllStudents()
// 		- printSortedStudentsByName() → uses StudentNameComparator
 
// Sample Input:
// -------------
// Tech University
// 1
// UG Alice 101 4
// CS101 DataStructures 4
// CS102 AI 3
// CS103 OS 4
// CS104 DBMS 3


// Sample Output:
// -------------
// Alice cannot register for more than 3 courses.
// All students:
// Alice (101)
//   Registered: CS101 - DataStructures
//   Registered: CS102 - AI
//   Registered: CS103 - OS
// Sorted students by name:
// Alice (101)
//   Registered: CS101 - DataStructures
//   Registered: CS102 - AI
//   Registered: CS103 - OS
import java.util.*;

// 🚫 DO NOT MODIFY THIS MAIN CLASS
public class UniversityApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String universityName = sc.nextLine();
        int studentCount = Integer.parseInt(sc.nextLine());

        University university = new University(universityName);

        for (int i = 0; i < studentCount; i++) {
            String[] studentInfo = sc.nextLine().split(" ");
            String type = studentInfo[0];
            String name = studentInfo[1];
            int id = Integer.parseInt(studentInfo[2]);
            int courseCount = Integer.parseInt(studentInfo[3]);

            Student student = null;
            if (type.equalsIgnoreCase("UG")) {
                student = new Undergraduate(name, id);
            } else if (type.equalsIgnoreCase("PG")) {
                student = new Postgraduate(name, id);
            }

            for (int j = 0; j < courseCount; j++) {
                String[] courseInfo = sc.nextLine().split(" ");
                Course c = new Course(courseInfo[0], courseInfo[1], Integer.parseInt(courseInfo[2]));
                try {
                    student.registerCourse(c);
                } catch (CourseLimitExceededException e) {
                    System.out.println(e.getMessage());
                }
            }

            university.addStudent(student);
        }

        System.out.println("All students:");
        university.printAllStudents();

        System.out.println("\nSorted students by name:");
        university.printSortedStudentsByName();
    }
}

// TODO: Define the Printable interface with one method: void printDetails()
interface Printable {
    void printDetails();
}


// TODO: Implement class Course with fields: code, title, credits
// Add constructor and override toString() to return "code - title"
class Course {
    // TODO: Declare fields and constructor
    // TODO: Override toString()
    private String code;
    private String title;
    private int credits;
    Course(String code,String title,int credits){
        this.code = code;
        this.title = title;
        this.credits = credits;
    }
    public String getCode(){
        return this.code;
    }
    public String getTitle(){
        return this.getTitle();
    }
    public int getCredits(){
        return this.credits;
    }
    
    @Override
    public String toString(){
        return code + " - " + title;
    }
}

// TODO: Create abstract class Student implementing Printable
// Include: name, id, list of courses, abstract int getMaxCourses()
// Method: registerCourse(Course) throws CourseLimitExceededException
// Override toString() to return "name (id)"
// Implement printDetails()
abstract class Student implements Printable {
    // TODO: Define fields, constructor, and methods as per the spec
    protected String name;
    protected int id;
    protected List<Course> cl;
    Student(String name,int id){
        this.name = name;
        this.id = id;
        this.cl = new ArrayList<>();
    }
    abstract int getMaxCourses();
    protected void registerCourse(Course c) throws CourseLimitExceededException{
        if(this.cl.size()==getMaxCourses()){
            String msg = name + " cannot register for more than " + getMaxCourses() + " courses.";
            throw new CourseLimitExceededException(msg);
        }
        this.cl.add(c);
    }
    @Override
    public String toString(){
        return name + " (" + id + ")";
    }
    
    @Override
    public void printDetails(){
        for(Course c : cl){
            System.out.println("  Registered: "+ c);
        }
    }
    
    
    
}

// TODO: Implement Undergraduate class extending Student
// getMaxCourses() should return 3
class Undergraduate extends Student {
    // TODO
    Undergraduate(String name, int id){
        super(name,id);
    }
    protected int getMaxCourses(){
        return 3;
    }
}

// TODO: Implement Postgraduate class extending Student
// getMaxCourses() should return 2
class Postgraduate extends Student {
    // TODO
    Postgraduate(String name,int id){
        super(name,id);
    }
    protected int getMaxCourses(){
        return 2;
    }
}

// TODO: Define custom exception class CourseLimitExceededException
class CourseLimitExceededException extends Exception {
    // TODO: Constructor accepting message
    CourseLimitExceededException(String msg){
        super(msg);
    }
}

// TODO: Implement Comparator<Student> to sort by student name
class StudentNameComparator implements Comparator<Student> {
    // TODO: Implement compare method
    @Override
    public int compare(Student a,Student b){
        return a.name.compareTo(b.name);
    }
}

// TODO: Implement University class implementing Printable
// Fields: name, list of students
// Methods: addStudent, printAllStudents, printSortedStudentsByName
class University implements Printable {
    // TODO
    private String name;
    private List<Student> sl;
    University(String name){
        this.name = name;
        this.sl = new ArrayList<>();
    }
    public void addStudent(Student s){
        this.sl.add(s);
    }
    
    public void printAllStudents(){
        for(Student s : sl){
            System.out.println(s);
            s.printDetails();
        }   
    }
    
    public void printSortedStudentsByName(){
        List<Student> l = new ArrayList<>(sl);
        
        Collections.sort(l,new StudentNameComparator());
        for(Student ss : l){
            System.out.println(ss);
            ss.printDetails();
        }
    }
    
    @Override
    public void printDetails(){
        return;
    }
    
    
    
}
