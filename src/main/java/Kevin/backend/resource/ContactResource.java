package Kevin.backend.resource;

import Kevin.backend.domain.Contact;
import Kevin.backend.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import static Kevin.backend.constant.Constant.PHOTO_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController // Marks this class as a RESTful web controller, allowing it to handle HTTP requests
@RequestMapping("/contacts") // Maps incoming requests to /contacts URL to the methods in this class
@RequiredArgsConstructor // Generates a constructor for all final fields, ensuring dependency injection for 'contactService'

public class ContactResource {
    private final ContactService contactService; // Service to handle business logic for Contact entity

    @PostMapping // Handles HTTP POST requests for creating a new contact
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        // Returns a 201 Created status with the newly created contact's details in the response body
        return ResponseEntity.created(URI.create("/contacts/userID")).body(contactService.createContact(contact));
    }

    @GetMapping // Handles HTTP GET requests to retrieve paginated contacts
    public ResponseEntity<Page<Contact>> getContacts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "size", defaultValue = "10") int size) {
        // Returns the paginated list of contacts in the response body
        return ResponseEntity.ok().body(contactService.getAllContacts(page, size));
    }

    @GetMapping("/{id}") // Handles HTTP GET requests to retrieve a contact by its ID
    public ResponseEntity<Contact> getContact(@PathVariable(value = "id") String id) {
        // Returns the contact corresponding to the provided ID in the response body
        return ResponseEntity.ok().body(contactService.getContact(id));
    }

    @PutMapping("/photo") // Handles HTTP PUT requests for uploading a photo for a contact
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam("file") MultipartFile file) {
        // Uploads the contact's photo and returns the photo URL in the response body
        return ResponseEntity.ok().body(contactService.uploadPhoto(id, file));
    }

    @GetMapping(path = "/image/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    // Handles HTTP GET requests to retrieve a photo by filename, supports both PNG and JPEG
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        // Reads the image file from the specified directory and returns it as a byte array
        return Files.readAllBytes(Paths.get(PHOTO_DIRECTORY + filename));
    }
}
