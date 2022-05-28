package uz.ages.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uz.ages.appjparelationship.entity.Group;

import java.lang.annotation.Native;
import java.util.List;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group, Integer> {

//    List<Group> findAllByFaculty_University_Id(Integer faculty_university_id);

    //
//    @Modifying
//    @Query("select gr from groups gr where gr.faculty.university.id = :universityId")
//    List<Group> getGroupsByUniversityId(Integer universityId);
//
//    @Query(nativeQuery=true, value = "select from groups g" +
//            "join faculty f on f.id = g.faculty_id " +
//            "join university u on u.id = f.university_id")
//    List<Group> getGroupsByUniversityIdNative(Integer universityId);
}

