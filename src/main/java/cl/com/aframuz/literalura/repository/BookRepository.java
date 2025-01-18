package cl.com.aframuz.literalura.repository;

import cl.com.aframuz.literalura.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface BookRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE LOWER(l.title) = LOWER(:title)")
    Libro findByTitle(String title);

    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findAliveAuthors(@Param("year") Year year);

    @Query("SELECT a FROM Author a WHERE a.birthYear = :year AND (a.deathYear IS NULL OR a.deathYear >= :year)")
    List<Author> findAliveAuthosRefined(@Param("year") Year year);

    @Query("SELECT l FROM Libro l WHERE l.languages LIKE %:language%")
    List<Libro> findByLanguage(@Param("language") String language);

}
