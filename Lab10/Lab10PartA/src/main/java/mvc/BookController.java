package mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class BookController {

    private Map<String, Book> books = new HashMap<>();

    public BookController() {
        Book theCatcherInTheRye = new Book("978-0-316-76948-0", "J.D. Salinger", "The Catcher in the Rye", 5.94);
        books.put(theCatcherInTheRye.getTitle(), theCatcherInTheRye);
        Book toKillAMockingbird = new Book("978-0-06-112008-4", "Harper Lee", "To Kill a Mockingbird", 85.44);
        books.put(toKillAMockingbird.getTitle(), toKillAMockingbird);
    }

    @GetMapping("/books/{title}")
    public ResponseEntity<?> getBook(@PathVariable String title) {
        Book book = books.get(title);
        if (book == null) {
            return new ResponseEntity<>(new CustomErrorType("Book with title= "
                    + title + " is not available"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        books.put(book.getTitle(), book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @DeleteMapping("/books/{title}")
    public ResponseEntity<?> deleteBook(@PathVariable String title) {
        Book book = books.get(title);
        if (book == null) {
            return new ResponseEntity<>(new CustomErrorType("Book with title= " + title + " is not available"), HttpStatus.NOT_FOUND);
        }
        books.remove(title);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/books/{title}")
    public ResponseEntity<?> updateBook(@PathVariable String title, @RequestBody Book book) {
        books.put(title, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        Books allBooks = new Books(books.values());
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @GetMapping("/books/searchByAuthor/{author}")
    public ResponseEntity<?> searchBooks(@PathVariable String author) {
        for (Book book : books.values()) {
            if (book.getAuthor().equals(author)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
