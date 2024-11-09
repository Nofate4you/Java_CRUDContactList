package Kevin.backend.repo;


import Kevin.backend.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Interface for Contact repository
@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // Find a contact by ID
    Optional<Contact> findById(String id);
}
