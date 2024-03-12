import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import mvc.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

    @Test
    public void testGetOneContact() {
        // add the contact to be fetched
        Book book = new Book("Mary", "Jones", "mjones@acme.com", "2341674376");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/contacts").then()
                .statusCode(200);
        // test getting the contact
        given()
                .when()
                .get("contacts/Mary")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("firstName",equalTo("Mary"))
                .body("lastName",equalTo("Jones"))
                .body("email",equalTo("mjones@acme.com"))
                .body("phone",equalTo("2341674376"));
        //cleanup
        given()
                .when()
                .delete("contacts/Mary");
    }

    @Test
    public void testDeleteContact() {
        // add the contact to be deleted book
        Book book = new Book("Bob", "Smith", "bobby@hotmail.com", "76528765498");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/contacts").then()
                .statusCode(200);

        given()
                .when()
                .delete("contacts/Bob");

        given()
                .when()
                .get("contacts/Bob")
                .then()
                .statusCode(404)
                .and()
                .body("errorMessage",equalTo("Contact with firstname= Bob is not available"));
    }

    @Test
    public void testAddContact() {
        // add the contact
        Book book = new Book("Bob", "Smith", "bobby@hotmail.com", "76528765498");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/contacts").then()
                .statusCode(200);
        // get the contact and verify
        given()
                .when()
                .get("contacts/Bob")
                .then()
                .statusCode(200)
                .and()
                .body("firstName",equalTo("Bob"))
                .body("lastName",equalTo("Smith"))
                .body("email",equalTo("bobby@hotmail.com"))
                .body("phone",equalTo("76528765498"));
        //cleanup
        given()
                .when()
                .delete("contacts/Bob");
    }

    @Test
    public void testUpdateContact() {
        // add the contact
        Book book = new Book("Bob", "Smith", "bobby@hotmail.com", "76528765498");
        Book updateBook = new Book("Bob", "Johnson", "bobby@gmail.com", "89765123");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/contacts").then()
                .statusCode(200);
        //update contact
        given()
                .contentType("application/json")
                .body(updateBook)
                .when().put("/contacts/"+ updateBook.getIsbn()).then()
                .statusCode(200);
        // get the contact and verify
        given()
                .when()
                .get("contacts/Bob")
                .then()
                .statusCode(200)
                .and()
                .body("firstName",equalTo("Bob"))
                .body("lastName",equalTo("Johnson"))
                .body("email",equalTo("bobby@gmail.com"))
                .body("phone",equalTo("89765123"));
        //cleanup
        given()
                .when()
                .delete("contacts/Bob");
    }

    @Test
    public void testGetAllContacts() {
        // add the contacts
        Book book = new Book("Bob", "Smith", "bobby@hotmail.com", "76528765498");
        Book book2 = new Book("Tom", "Johnson", "tomjohnson@gmail.com", "543256789");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/contacts").then()
                .statusCode(200);
        given()
                .contentType("application/json")
                .body(book2)
                .when().post("/contacts").then()
                .statusCode(200);

        // get all contacts and verify
        given()
                .when()
                .get("contacts")
                .then()
                .statusCode(200)
                .and()
                .body("contacts.firstName", hasItems("Bob", "Tom"))
                .body("contacts.lastName",hasItems("Smith", "Johnson"))
                .body("contacts.email",hasItems("bobby@hotmail.com", "tomjohnson@gmail.com"))
                .body("contacts.phone",hasItems("76528765498", "543256789"));
        //cleanup
        given()
                .when()
                .delete("contacts/Bob");
        given()
                .when()
                .delete("contacts/Tom");
    }

}
