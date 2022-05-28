package uz.ages.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.ages.appjparelationship.entity.Faculty;
import uz.ages.appjparelationship.entity.University;
import uz.ages.appjparelationship.payload.FacultyDto;
import uz.ages.appjparelationship.repository.FacultyRepository;
import uz.ages.appjparelationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RequestMapping("/faculty")
@RestController
public class FacultyController {

    @Autowired
    FacultyRepository facultyRepository;

    @Autowired
    UniversityRepository universityRepository;

    //vazirlik uchumm
    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
//        if (facultyRepository.existsByNameAndUniversity_id(facultyDto.getName(), facultyDto.getUniversityId()))
//            return "This university such faculty exist";
        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> byId = universityRepository.findById(facultyDto.getUniversityId());
        if (byId.isEmpty())
            return
                    "University not found";
        faculty.setUniversity(byId.get());

        facultyRepository.save(faculty);
        return "Faculty added.";
    }

    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Long id) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            facultyRepository.deleteById(id);
            return "Facultet o'chirildi.";
        }
        return "Bunday facultet avval o'chirilga. Malumotlar omborida yo'q.";
    }

    @PutMapping("/{id}")
    public String editFaculty(@PathVariable Long id, @RequestBody FacultyDto facultyDto) {
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()) {
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent())
                return "Univesity not found.";
            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty edited.";
        }
        return "Faculty not found.";
    }
}


