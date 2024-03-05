package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findByDepartmentName(String departmentName);
    List<Student> findByGradesCourseName(String courseName);
}
