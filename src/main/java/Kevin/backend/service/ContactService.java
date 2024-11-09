package Kevin.backend.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import Kevin.backend.domain.Contact;
import Kevin.backend.repo.ContactRepo;

import static Kevin.backend.constant.Constant.PHOTO_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service // Marks the class as a service in Spring, making it available for dependency injection
@Slf4j // Enables logging (log.info, log.error) via Lombok
@Transactional(rollbackOn = Exception.class) // Ensures all database operations are part of a transaction and rollback on exception
@RequiredArgsConstructor // Generates a constructor with required arguments for final fields, specifically 'contactRepo'
public class ContactService {
    // Repository to handle CRUD operations for Contact entities
    private final ContactRepo contactRepo;

    // Method to retrieve paginated contacts, sorted by name
    public Page<Contact> getAllContacts(int page, int size) {
        // Creates a PageRequest for pagination and sorting by 'name'
        return contactRepo.findAll(PageRequest.of(page, size, Sort.by("name")));
    }

    // Method to fetch a contact by its ID, or throw an exception if not found
    public Contact getContact(String id) {
        // Retrieves contact by ID or throws a RuntimeException if not found
        return contactRepo.findById(id).orElseThrow(() -> new RuntimeException("Contact not found"));
    }

    // Method to save a new contact to the database
    public Contact createContact(Contact contact) {
        // Saves the contact and returns the saved entity
        return contactRepo.save(contact);
    }

    // Placeholder method for deleting a contact (currently not implemented)
    public void deleteContact(Contact contact) {
        // Assignment (this method has yet to be implemented)
    }

    // Method to handle uploading a contact's profile photo
    public String uploadPhoto(String id, MultipartFile file) {
        // Logs an informational message with the user ID
        log.info("Saving picture for user ID: {}", id);

        // Retrieves the contact by ID
        Contact contact = getContact(id);

        // Generates a photo URL by applying the 'photoFunction'
        String photoUrl = photoFunction.apply(id, file);

        // Sets the photo URL for the contact and updates it in the database
        contact.setPhotoUrl(photoUrl);
        contactRepo.save(contact);

        // Returns the generated photo URL
        return photoUrl;
    }

    // Function to get the file extension from a filename, defaults to .png if no extension is found
    private final Function<String, String> fileExtension = filename -> Optional.of(filename)
            .filter(name -> name.contains(".")) // Checks if filename contains a period (.)
            .map(name -> "." + name.substring(filename.lastIndexOf(".") + 1)) // Extracts the extension
            .orElse(".png"); // Defaults to ".png" if no extension is found

    // BiFunction to handle saving an image file and returning the photo's URL
    private final BiFunction<String, MultipartFile, String> photoFunction = (id, image) -> {
        // Generates a filename using the contact ID and the file extension
        String filename = id + fileExtension.apply(image.getOriginalFilename());
        try {
            // Defines the location where the image will be saved
            Path fileStorageLocation = Paths.get(PHOTO_DIRECTORY).toAbsolutePath().normalize();

            // Creates the directory if it does not exist
            if(!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }

            // Copies the image file to the storage location, replacing any existing file with the same name
            Files.copy(image.getInputStream(), fileStorageLocation.resolve(filename), REPLACE_EXISTING);

            // Builds and returns the URL for accessing the uploaded image
            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/contacts/image/" + filename)
                    .toUriString();
        } catch (Exception exception) {
            // Throws a RuntimeException if there is an error during file saving
            throw new RuntimeException("Unable to save image");
        }
    };
}

