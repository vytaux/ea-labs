package domain;


import jakarta.persistence.Entity;

@Entity
public class Book extends Product {

	private String isbn;

	public Book() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String artist) {
		this.isbn = artist;
	}
}
