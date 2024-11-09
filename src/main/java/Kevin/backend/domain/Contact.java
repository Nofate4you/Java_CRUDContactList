package Kevin.backend.domain;


// Import necessary libraries for JSON, JPA, Lombok, and Hibernate
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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT) // Excludes fields with null or default values from JSON output
@Table(name = "contacts") // Maps this entity to the "contacts" table in the database
public class Contact {

    @Id
    @UuidGenerator
    @Column(name = "id", unique = true, updatable = false)
    private String id;

    private String name; // Name of the contact
    private String email;
    private String title; // Title or designation of the contact
    private String phone;
    private String address;
    private String status; // Status (e.g., active, inactive) of the contact
    private String photoUrl; // URL for the contact's photo
}
