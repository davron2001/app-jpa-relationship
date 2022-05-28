package uz.ages.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.ages.appjparelationship.entity.Faculty;
import uz.ages.appjparelationship.entity.Group;
import uz.ages.appjparelationship.payload.GroupDto;
import uz.ages.appjparelationship.repository.FacultyRepository;
import uz.ages.appjparelationship.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    FacultyRepository facultyRepository;

    //vazirlik uchun
    //read
    @GetMapping
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    //Universitetni masul xodimi uchun
    @GetMapping("/byUniversity/{universityId}")
    public List<Group> getGroupsByUniversityId(@PathVariable Integer universityId) {
//        List<Group> allByFaculty_university_id = groupRepository.findAllByFaculty_University_Id(universityId);
//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
//        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);

        return new ArrayList<>();
    }

    @PostMapping
    public String addGroup(@RequestBody GroupDto groupDto) {
        Group group = new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> byId = facultyRepository.findById(groupDto.getFacultyId());

        if (!byId.isPresent())
            return "Such faculty not found";
        Faculty faculty = byId.get();
        group.setFaculty(faculty);
        groupRepository.save(group);
        return "Group saved";
    }
}
