package cl.com.aframuz.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorDTO(
        String name,
        @JsonAlias("birth_year") Integer birthYear,
        @JsonAlias("death_year") Integer deathYear) {
}

