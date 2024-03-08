package mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class ContactController {

    private Map<String, Contact> contacts = new HashMap<String, Contact>();

    public ContactController() {
        contacts.put("Frank", new Contact("Frank", "Brown", "fbrown@acme.com", "2341678453"));
        contacts.put("Mary", new Contact("Mary", "Jones", "mjones@acme.com", "2341674376"));
    }

    @GetMapping("/contacts/{firstName}")
    public ResponseEntity<?> getContact(@PathVariable String firstName) {
        Contact contact = contacts.get(firstName);
        if (contact == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Contact with firstname= "
                    + firstName + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @PostMapping("/contacts")
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        contacts.put(contact.getFirstName(), contact);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @DeleteMapping("/contacts/{firstName}")
    public ResponseEntity<?> deleteContact(@PathVariable String firstName) {
        Contact contact = contacts.get(firstName);
        if (contact == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Contact with firstname= " + firstName + " is not available"),HttpStatus.NOT_FOUND);
        }
        contacts.remove(firstName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/contacts/{firstName}")
    public ResponseEntity<?> updateContact(@PathVariable String firstName, @RequestBody Contact contact) {
        contacts.put(firstName, contact);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @GetMapping("/contacts")
    public ResponseEntity<?> getAllContacts() {
        Contacts allContacs = new Contacts(contacts.values());
        return new ResponseEntity<Contacts>(allContacs, HttpStatus.OK);
    }

}
