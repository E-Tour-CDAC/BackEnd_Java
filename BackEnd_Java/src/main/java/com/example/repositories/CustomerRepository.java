package com.example.repositories;

import com.example.entities.CustomerMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
<<<<<<< HEAD

@Repository
public interface CustomerRepository extends JpaRepository<CustomerMaster, Integer> {

=======
@Repository
public interface CustomerRepository extends JpaRepository<CustomerMaster,Integer> {
>>>>>>> 72611e12090a56c25edf5241cb23cf14338af9c0

    Optional<CustomerMaster> findByEmail(String email); //Find by Email

    // Assuming we might want to find by name too?
    //Optional<CustomerMaster> findByFirstLastName(String firstName,String LastName);
}
