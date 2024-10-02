package com.spring.mongo;

import com.spring.mongo.entity.Movie;
import com.spring.mongo.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class MongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
		System.out.println("MongoApplication is running...");
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoDatabaseFactory databaseFactory,
									   MappingMongoConverter converter) {
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return new MongoTemplate(databaseFactory, converter);
	}

	@Bean
	CommandLineRunner commandLineRunner(MovieRepository movieRepository) {
		return args -> {

			List<Movie> movies = Arrays.asList(
					new Movie("Inception", "Christopher Nolan",
							LocalDate.of(2010, 7, 16), new HashSet<>(Arrays.asList("Sci-Fi", "Action")), 8.8, 829895144L),
					new Movie("The Matrix", "Lana Wachowski",
							LocalDate.of(1999, 3, 31), new HashSet<>(Arrays.asList("Sci-Fi", "Action")), 8.7, 463517383L),
					new Movie("The Upside", "Neil Burger",
							LocalDate.of(2019, 1, 10), new HashSet<>(Arrays.asList("Drama", "Comedy")), 6.9, 107187120L),
					new Movie("Interstellar", "Christopher Nolan",
							LocalDate.of(2014, 11, 7), new HashSet<>(Arrays.asList("Sci-Fi", "Drama")), 8.6, 677471339L)
			);

			movieRepository.saveAll(movies);

		};
	}

}
