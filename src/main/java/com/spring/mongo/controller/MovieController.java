package com.spring.mongo.controller;

import com.spring.mongo.entity.Movie;
import com.spring.mongo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Get all movies
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    // Get a single movie by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable String id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    // Create a new movie
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.createMovie(movie));
    }

    // Update an existing movie
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.updateMovie(id, movie));
    }

    // Delete a movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    // Custom query: Find movies by title and director
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> findMoviesByTitleAndDirector(@RequestParam(value = "query") String query) {
        return ResponseEntity.ok(movieService.searchMoviesByText(query));
    }

    // Custom query: Find movies by title
    @GetMapping("/title")
    public ResponseEntity<List<Movie>> findMoviesByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(movieService.findMoviesByTitle(title));
    }

    // Custom query: Find movies by rating greater than or equal
    @GetMapping("/rating")
    public ResponseEntity<List<Movie>> findMoviesByRatingGreaterThanEqual(@RequestParam("minRating") double minRating) {
        return ResponseEntity.ok(movieService.findMoviesByRatingGreaterThanEqual(minRating));
    }

    // Custom query: Find movies by genre
    @GetMapping("/genre")
    public ResponseEntity<List<Movie>> findMoviesByGenresContaining(@RequestParam("genre") String genre) {
        return ResponseEntity.ok(movieService.findMoviesByGenresContaining(genre));
    }

}
