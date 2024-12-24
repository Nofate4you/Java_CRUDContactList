package Kevin.backend.domain;

// Import necessary libraries for JSON handling, JPA (Java Persistence API), Lombok, and Hibernate
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT; // Includes only non-default values in JSON

// Annotating the class as a JPA entity, meaning it will be mapped to a database table
@Entity
@Getter // Lombok annotation to generate getter methods
@Setter // Lombok annotation to generate setter methods
@NoArgsConstructor // Lombok annotation to generate a no-args constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all arguments
@JsonInclude(NON_DEFAULT) // Excludes fields with null or default values from the JSON output
@Table(name = "contacts") // Maps this entity to the "contacts" table in the database
public class Contact {

    // Defines the primary key of the entity with a UUID
    @Id
    @UuidGenerator // Automatically generates a UUID for this field
    @Column(name = "id", unique = true, updatable = false) // Specifies the column details for the ID
    private String id;

    private String name; // Contact's name
    private String email; // Contact's email address
    private String title; // Title or designation of the contact
    private String phone; // Contact's phone number
    private String address; // Contact's physical address
    private String status; // Contact's status (e.g., active, inactive)
    private String photoUrl; // URL for the contact's photo
}
