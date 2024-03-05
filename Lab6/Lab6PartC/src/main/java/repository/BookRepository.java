package repository;

import domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Transactional
    @Query("update Book b set b.locationCode = CONCAT(:prefix, b.locationCode)")
    void updateLocationCodeWithPrefix(@Param("prefix") String prefix);

    @Modifying
    @Transactional
    @Query("delete from Book b where b.publicationYear < 1950")
    void deleteWherePublicationYearLessThan1950();
}
