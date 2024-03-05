package repositories;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, String> {
    List<CD> findByArtistAndPriceLessThan(String artist, double price);
    List<CD> findByArtist(String artist);

    @Query("SELECT c FROM CD c WHERE c.artist = :artist AND c.price > :price")
    List<CD> findByArtistAndPriceGreaterThan(String artist, double price);

    @Query(value = "SELECT * FROM Product p WHERE p.artist = 'U2' AND p.dtype='CD'", nativeQuery = true)
    List<CD> findByArtistU2();
}
