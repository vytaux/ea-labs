package domain;


import jakarta.persistence.*;

@Entity
@NamedQuery(
		name = "CD.findByArtist",
		query = "SELECT cd FROM CD cd WHERE cd.artist = :artist"
)
public class CD extends Product {


	private String artist;

	public CD() {
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}
}
