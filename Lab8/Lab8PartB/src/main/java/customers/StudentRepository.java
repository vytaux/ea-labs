package customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {

    List<Student> findByName(String name);
    List<Student> findByPhone(String phone);

    @Query("{'address.city' : :#{#city}}")
    List<Student> findByAddressCity(@Param("city") String city);

    @Query("{'grades.courseName' : :#{#courseName}}")
    List<Student> findByGradesCourseName(@Param("courseName") String courseName);

    @Query("{'$and' : [{'grades.courseName' : :#{#courseName}}, {'grades.grade' : :#{#grade}}]}")
    List<Student> findByGradesCourseNameAndGrade(@Param("courseName") String courseName, @Param("grade") String grade);
}

