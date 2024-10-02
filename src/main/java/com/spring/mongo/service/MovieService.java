package com.spring.mongo.service;

import com.spring.mongo.entity.Movie;
import com.spring.mongo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // CRUD Operations
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(String id, Movie updatedMovie) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            movie.setTitle(updatedMovie.getTitle());
            movie.setDirector(updatedMovie.getDirector());
            movie.setReleaseDate(updatedMovie.getReleaseDate());
            movie.setGenres(updatedMovie.getGenres());
            movie.setRating(updatedMovie.getRating());
            movie.setBoxoffice(updatedMovie.getBoxoffice());
            return movieRepository.save(movie);
        }
        return null;
    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }

    // Custom Queries
    public List<Movie> searchMoviesByText(String query) {
        return movieRepository.findByTextSearch(query);
    }

    public List<Movie> findMoviesByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public List<Movie> findMoviesByRatingGreaterThanEqual(double minRating) {
        return movieRepository.findByRatingGreaterThanEqual(minRating);
    }

    public List<Movie> findMoviesByGenresContaining(String genre) {
        return movieRepository.findByGenresContaining(genre);
    }

}
