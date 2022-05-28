package uz.ages.appjparelationship.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.appjparelationship.entity.Student;
import uz.ages.appjparelationship.entity.University;

@Repository
public interface UniversityRepository extends JpaRepository<University, Integer> {
}
