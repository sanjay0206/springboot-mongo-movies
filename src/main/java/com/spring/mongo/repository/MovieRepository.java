package com.spring.mongo.repository;

import com.spring.mongo.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    // Text search for title and director
    @Query("{ '$text': { '$search': ?0 } }")
    List<Movie> findByTextSearch(String query);

    // Find by title
    @Query("{ 'title': ?0 }")
    List<Movie> findByTitle(String title);

    // Find by rating greater than or equal to a given value
    @Query("{ 'rating': { $gte: ?0 } }")
    List<Movie> findByRatingGreaterThanEqual(double rating);

    // Find movies by genres containing a specific genre
    @Query("{ 'genres': { $elemMatch: { $eq: ?0 } } }")
    List<Movie> findByGenresContaining(String genre);

}
