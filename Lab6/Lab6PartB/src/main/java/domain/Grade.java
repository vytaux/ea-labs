package domain;

import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private Long id;

    private double grade;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Course course;

    public Grade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                ", course=" + course +
                '}';
    }
}
