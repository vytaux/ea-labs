package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/books";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// get To Kill a Mockingbird
		Book book = restTemplate.getForObject(serverUrl+"/{title}", Book.class, "To Kill a Mockingbird");
		System.out.println(book);

		// add Ttile
		restTemplate.postForLocation(serverUrl, new Book("123454", "Aauthor", "Ttile", 4.51));

		// get Ttile
		book = restTemplate.getForObject(serverUrl+"/{title}", Book.class, "Ttile");
		System.out.println(book);

		// delete The Catcher in the Rye
		restTemplate.delete(serverUrl+"/{title}", "The Catcher in the Rye");

		// update Ttile
		book.setAuthor("Other Author");
		restTemplate.put(serverUrl+"/{title}" , book, "Ttile");

		// get Ttile
		book = restTemplate.getForObject(serverUrl+"/{title}", Book.class, "Ttile");
		System.out.println(book);

        // get all books
		Books books = restTemplate.getForObject(serverUrl, Books.class);
		System.out.println(books);

        // Search by author
		Book byAuthor = restTemplate.getForObject(serverUrl + "/searchByAuthor/{author}", Book.class, "Harper Lee");
		System.out.println(byAuthor);
	}

}
