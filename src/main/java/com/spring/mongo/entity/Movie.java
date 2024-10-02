package com.spring.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Set;

@Data
@Document(collection = "movies")
public class Movie {

    @Id
    private String id;

    @Indexed(unique = true)
    @TextIndexed
    @Field("title")
    private String title;

    @TextIndexed
    @Field("director")
    private String director;

    @Field("release_date")
    private LocalDate releaseDate;

    @Field("genres")
    private Set<String> genres;

    @Field("rating")
    private Double rating;

    @Field("boxoffice")
    private long boxoffice;

    public Movie(String title, String director, LocalDate releaseDate, Set<String> genres, Double rating, long boxoffice) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.rating = rating;
        this.boxoffice = boxoffice;
    }
}
