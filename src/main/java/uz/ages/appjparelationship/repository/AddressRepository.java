package uz.ages.appjparelationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ages.appjparelationship.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
