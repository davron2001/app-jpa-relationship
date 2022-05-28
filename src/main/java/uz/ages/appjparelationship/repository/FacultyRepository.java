package uz.ages.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.appjparelationship.entity.Faculty;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

//    List<Faculty> findAllByUniversityId(Integer university_id);
}
