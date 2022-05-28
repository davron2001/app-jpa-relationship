package uz.ages.appjparelationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.ages.appjparelationship.entity.Address;
import uz.ages.appjparelationship.entity.University;
import uz.ages.appjparelationship.payload.UniversityDto;
import uz.ages.appjparelationship.repository.AddressRepository;
import uz.ages.appjparelationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;

    /**
     * Get all unuversity
     *
     * @return List<University>
     */
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getAllUniversity() {
        return universityRepository.findAll();
    }

    /**
     * Get one university by id
     *
     * @param id
     * @return University
     */
    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    public University getUniversityById(@PathVariable Integer id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        return new University();
    }


    /**
     * add University
     *
     * @param universityDto
     * @return String
     */
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address(
                universityDto.getCity(),
                universityDto.getDistrict(),
                universityDto.getStreet()
        );
        addressRepository.save(address);
        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(address);
        universityRepository.save(university);
        return "University added.";
    }


    /**
     * Update university
     *
     * @param id
     * @param universityDto
     * @return String
     */
    @RequestMapping(value = "/university/{id}", method = RequestMethod.PUT)
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()) {
            University university = byId.get();
            university.setName(universityDto.getName());
            Address address = new Address(
                    universityDto.getCity(),
                    universityDto.getDistrict(),
                    universityDto.getStreet()
            );
            addressRepository.save(address);
            university.setAddress(address);
            universityRepository.save(university);
            return "University updated";
        }
        return "University not found.";
    }

    /**
     * Delete university
     *
     * @param id
     * @return String
     */

    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deletedUniversity(@PathVariable Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()) {
            universityRepository.deleteById(id);
            return "University deleted";
        }
        return "University not found.";
    }
}
