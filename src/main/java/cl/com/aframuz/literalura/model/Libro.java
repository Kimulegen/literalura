package cl.com.aframuz.literalura.model;

import jakarta.persistence.*;

@Entity
@Table
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

    private String languages;

    private Integer birthYearAuthor;

    private Integer deathYearAuthor;

    private Integer downloadCount;

    // GETTERS & SETTERS
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public Author getAuthor() {return author;}

    public void setAuthor(Author author) {this.author = author;}

    public String getLanguage() {return languages;}

    public void setLanguage(String languages) {this.languages = languages;}

    public Integer getBirhYearAuthor() {return birthYearAuthor;}

    public void setBirhYearAuthor(Integer birthYearAuthor) {this.birthYearAuthor = birthYearAuthor;}

    public Integer getDeathYearAuthor() {return deathYearAuthor;}

    public void setDeathYearAuthor(Integer deathYearAuthor) {this.deathYearAuthor = deathYearAuthor;}

    public Integer getDownloadCount() {return downloadCount;}

    public void setDownloadCount(Integer downloadCount) {this.downloadCount = downloadCount;}

    // Construtores
    public Libro() {}

    public Libro(BookDTO bookDTO) {
        this.title = bookDTO.title();
        Author author = new Author(bookDTO.authors().get(0));
        this.author = author;
        this.languages = bookDTO.languages().get(0);
        this.downloadCount = bookDTO.downloadCount();
    }

    public Libro(Long id, String title, Author author, String languages, Integer downloadCount) {
        this.title = title;
        this.author = author;
        this.languages = languages;
        this.downloadCount = downloadCount;
    }


    // Methods
    @Override
    public String toString() {
        return String.format("""
                ----------- LIBRO --------------
                Titulo: %s
                Author: %s
                Idioma: %s
                Numero de descargas: %d
                -------------------------------""",
                title,
                author,
                languages,
                downloadCount);
    }
}
