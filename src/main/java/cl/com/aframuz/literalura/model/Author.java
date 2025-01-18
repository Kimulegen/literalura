package cl.com.aframuz.literalura.model;

import jakarta.persistence.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_year")
    private Year birthYear;

    @Column(name = "death_year")
    private Year deathYear;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Libro> books = new ArrayList<>();

    // GETTERS & SETTERS
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String author) {this.name = author;}

    public Year getBirthYear() {return birthYear;}

    public void setBirthYear(Year birthYear) {this.birthYear = birthYear;}

    public Year getDeathYear() {return deathYear;}

    public void setDeathYear(Year deathYear) {this.deathYear = deathYear;}

    public List<Libro> getBooks() {return books;}

    public void setBooks(List<Libro> books) {this.books = books;}

    // Construtores
    public Author() {}

    public Author(String name, Year birthYear, Year deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Author(AuthorDTO authorDTO) {
        this.name = authorDTO.name();
        this.birthYear = authorDTO.birthYear() != null ? Year.of(authorDTO.birthYear()) : null;
        this.deathYear = authorDTO.deathYear() != null ? Year.of(authorDTO.deathYear()) : null;
    }

    //Methods
    @Override
    public String toString() {
        String deathYearStr = deathYear != null ? deathYear.toString() : "sigue vivo!";
        String booksStr = this.books.stream()
                .map(Libro::getTitle)
                .reduce((a,b) -> a + ", " + b)
                .orElse("");

        return String.format("Author: %s\n" +
                        "Fecha de nacimiento: %s\n" +
                        "Fecha de fallecimiento: %s\n" +
                        "Libros: [%s]",
                name,
                birthYear.toString(),
                deathYearStr,
                booksStr);
    }
}
