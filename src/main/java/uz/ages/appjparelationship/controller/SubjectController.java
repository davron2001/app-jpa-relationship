package uz.ages.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.ages.appjparelationship.entity.Subject;
import uz.ages.appjparelationship.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    //READ

    /**
     * GET ALL SUBJECTS
     *
     * @return
     */
    @GetMapping
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    /**
     * GET ONE SUBJECT
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Subject getSubject(@PathVariable Integer id) {
        Optional<Subject> byId = subjectRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new Subject();
    }

    /**
     * ADD SUBJECT
     *
     * @param subject
     * @return String
     */
    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        boolean b = subjectRepository.existsByName(subject.getName());
        if (b) return "This subject already added.";
        subjectRepository.save(subject);
        return "Subject added";
    }

    /**
     * Updated subject
     *
     * @param id
     * @param subject
     * @return String
     */
    @PutMapping(value = "/{id}")
//    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        boolean b = subjectRepository.existsByName(subject.getName());
        if (b) return "This subject already added.";
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject1 = optionalSubject.get();
            subject1.setName(subject.getName());
            subjectRepository.save(subject1);
            return "Subject updated";
        }
        return "This id not found.";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            subjectRepository.delete(optionalSubject.get());
            return "Subject deleted.";
        }
        return "This id subject already deleted.";
    }
}
