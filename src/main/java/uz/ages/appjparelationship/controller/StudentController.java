package uz.ages.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.ages.appjparelationship.entity.*;
import uz.ages.appjparelationship.payload.StudentDto;
import uz.ages.appjparelationship.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //1. VAZIRLIK
    @GetMapping("/forMinistry")
    public Page<Student> getStudentListForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //2. University
    @GetMapping("/forUniversity")
    public Page<Student> getStudentListForUniversity(@PathVariable Integer universityId, @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepository.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirtName());
        student.setLastName(studentDto.getLastName());

        Address address = new Address();
        address.setCity(studentDto.getCity());
        address.setDistrict(studentDto.getDistrict());
        address.setStreet(studentDto.getStreet());
        addressRepository.save(address);
        student.setAddress(address);

        Optional<Group> optionalGroup = groupRepository.findById(studentDto.getGroupId());
        if (optionalGroup.isEmpty()) {
            return "Such group not found";
        }
//        Group group = optionalGroup.get();
//        student.setGroup(group);

        Optional<Subject> optionalSubject = subjectRepository.findById(studentDto.getSubjectId());
        if (optionalSubject.isEmpty())
            return "Such subject not found.";
        Subject subject = optionalSubject.get();
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        student.setSubjectList(subjects);
        studentRepository.save(student);

        return "Student added";
    }


}
