package cl.com.aframuz.literalura.principal;

import cl.com.aframuz.literalura.model.Author;
import cl.com.aframuz.literalura.model.AuthorDTO;
import cl.com.aframuz.literalura.model.Libro;
import cl.com.aframuz.literalura.model.BookDTO;
import cl.com.aframuz.literalura.repository.BookRepository;
import cl.com.aframuz.literalura.service.ConsumoAPI;
import cl.com.aframuz.literalura.service.ConvierteDatos;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Principal {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConvierteDatos convierteDatos;

    private final Scanner scanner = new Scanner(System.in);

    public Principal(BookRepository bookRepository, ConsumoAPI consumoAPI, ConvierteDatos convierteDatos) {
        this.bookRepository = bookRepository;
        this.consumoAPI = consumoAPI;
        this.convierteDatos = convierteDatos;
    }

    public void init() {
        boolean running = true;
        while (running) {
            showMenu();
            var option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> searchBooksWeb();
                case 2 -> showRegisteredBooks();
                case 3 -> showRegisterdAuthors();
                case 4 -> showAuthorsByYear();
                case 5 -> showBooksByLanguage();
                case 0 -> {
                    System.out.println("Cerrando la aplicación...!");
                    running = false;
                }
                default -> System.out.println("Opción inválida!");
            }
        }
    }

    private void saveBook(Libro book) {
        bookRepository.save(book);
    }

    private void showBooksByLanguage() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);
        String language = scanner.nextLine();

        List<Libro> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("No libros encontrados con el idioma: " + language);
        } else {
            books.forEach(System.out::println);
        }
    }

    private void showAuthorsByYear() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar:");
        Integer year = scanner.nextInt();
        scanner.nextLine();

        Year toYear = Year.of(year);

        List<Author> authors = bookRepository.findAliveAuthors(toYear);
        if (authors.isEmpty()) {
            System.out.println("No hay autores vivos encontrados con esa fecha!");
        } else {
            System.out.println("Lista de autores vivos encontrados en el año " + toYear + "\n");
            authors.forEach(author -> {
                System.out.println("--------------------------");
                System.out.println(author);
                System.out.println("--------------------------");
                System.out.println();
            });
        }
    }

    private void showRegisterdAuthors() {
        List<Libro> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados!");
        } else {
            books.stream()
                    .map(Libro::getAuthor)
                    .distinct()
                    .forEach(author -> {
                        System.out.println("--------------------------");
                        System.out.println(author);
                        System.out.println("--------------------------");
                        System.out.println();
                    });
        }
    }

    private void showRegisteredBooks() {
        List<Libro> books = bookRepository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados!");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void searchBooksWeb() {
        String baseURL = "https://gutendex.com/books?search=";

        try {
            System.out.println("Ingrese el nombre del libro que desea buscar: ");
            String title = scanner.nextLine();
            String url = baseURL + title.replace(" ", "%20");
            System.out.println("URL del libro a buscar:  " + url);

            String jsonResponse = consumoAPI.getDatos(url);

            if (jsonResponse.isEmpty()) {
                System.out.println("La respuesta de la API está vacía!");
                return;
            }

            ObjectNode firstBookFound = (ObjectNode) convierteDatos.getObjectMapper()
                    .readTree(jsonResponse)
                    .get("results")
                    .get(0);

            if (firstBookFound == null) {
                System.out.println("Libro no encontrado");
                return;
            }

            BookDTO bookFound = convierteDatos.getObjectMapper()
                    .convertValue(firstBookFound, BookDTO.class);

            // See if it exists in the database
            Libro book = bookRepository.findByTitle(bookFound.title());

            if (book != null) {
                System.out.println("No se puede registrar el mismo libro más de una vez");
                System.out.println("Libro que trató de registrar: " + bookFound.title());
                System.out.println("Porfavor refine su búsqueda");
                return;
            }

            System.out.println("Libro encontrado: \n" + bookFound);

            // Storing book
            saveBook(new Libro(bookFound));
            System.out.println("Libro guardado!");
            // Storing books in DB
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showMenu() {
        var menu = """
                    Elija la opción a través de su número
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma

                    0 - Salir
                    """;
        System.out.println(menu);
    }


}
