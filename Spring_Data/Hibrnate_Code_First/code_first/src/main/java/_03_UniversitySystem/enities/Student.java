package _03_UniversitySystem.enities;

import javax.persistence.*;
import java.util.Collections;
import java.util.Set;

@Entity(name = "students")
public class Student extends Person {

    @Column(name = "average_grade")
    private double averageGrade;

    private int attendance;

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    public Set<Course> getCourses() {
        return Collections.unmodifiableSet(courses);
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Student() {
        super();
    }

    public Student(String firstName, String lastname, String phoneNumber, double averageGrade, int attendance) {
        super(firstName, lastname, phoneNumber);
        setAverageGrade(averageGrade);
        setAttendance(attendance);
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
