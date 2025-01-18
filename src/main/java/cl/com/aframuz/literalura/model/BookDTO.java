package cl.com.aframuz.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        String title,
        @JsonAlias("download_count") Integer downloadCount,
        @JsonAlias("languages") List<String> languages,
        List<AuthorDTO> authors)
{}

