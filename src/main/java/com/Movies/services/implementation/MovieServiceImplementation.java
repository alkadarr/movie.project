package com.Movies.services.implementation;

import com.Movies.dtos.MetaDataDto;
import com.Movies.dtos.PageTemplate;
import com.Movies.dtos.movie.*;
import com.Movies.helper.Formatter;
import com.Movies.models.Actor;
import com.Movies.models.Movie;
import com.Movies.repositories.*;
import com.Movies.services.abstraction.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImplementation implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<MovieDetailsDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDetailsDto> moviesDto = new ArrayList<>();

        for (var movie : movies) {
            moviesDto.add(new MovieDetailsDto(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getProductionYear(),
                    movie.getDuration(),
                    movie.getLanguage(),
                    movie.getReleaseDate(),
                    movie.getReleaseCountry(),
                    movieRepository.getAverageRating(movie.getId()),
                    movie.getAllDirectorsName(),
                    movie.getAllActorsName(),
                    movie.getAllGenreTitles()
            ));
        }
        return moviesDto;
    }

    @Override
    public Object getAllMoviesWithPagination(String search, String releaseCountry, Integer page, Integer size, String sortBy, String sortType) {
        PageTemplate result = new PageTemplate();
        MetaDataDto meta = new MetaDataDto();

        PageRequest pagination = PageRequest.of(
                page-1,
                size,
                Sort.by(Sort.Direction.fromString(sortType), sortBy));

        Page<Movie> movies = movieRepository.getMovieForPage(
                search,
                releaseCountry,
                pagination
        );

        List<MovieGridDto> movieGridDtos = new ArrayList<>();
        for (var movie : movies){
            movieGridDtos.add(
                    new MovieGridDto(
                            movie.getId(),movie.getTitle(),movie.getProductionYear(),
                            movie.getDuration(),movie.getLanguage(),movie.getReleaseDate(),
                            movie.getReleaseCountry(),movie.getRatingRate(),movie.getAllGenreTitles()
                    )
            );
        }

        meta.setTotalCount(movies.getTotalElements());
        meta.setPageCount(movies.getTotalPages());
        meta.setCurrentPage(page);
        meta.setPerPage(size);

        result.set_items(movies.getTotalPages() == 0 ? null : movieGridDtos);
        result.set_meta(meta);

        return result;
    }

    @Override
    public MovieDetailsDto getMovieById(String id) {
        return movieRepository.findById(id)
                .map(movie -> new MovieDetailsDto(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getProductionYear(),
                        movie.getDuration(),
                        movie.getLanguage(),
                        movie.getReleaseDate(),
                        movie.getReleaseCountry(),
                        movieRepository.getAverageRating(movie.getId()),
                        movie.getAllDirectorsName(),
                        movie.getAllActorsName(),
                        movie.getAllGenreTitles()
                )).orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    @Override
    public MovieInsertDto insertMovie(MovieInsertDto dto) {
        boolean cek = movieRepository.findById(dto.getId()).isPresent();
        if (cek) {
            throw new EntityNotFoundException(String.format("Movie with id %s already exists", dto.getId()));
        } else {
            Movie movie = new Movie();

            movie.setId(dto.getId());
            movie.setTitle(dto.getTitle());
            movie.setProductionYear(dto.getProductionYear());
            movie.setDuration(dto.getDuration());
            movie.setLanguage(dto.getLanguage());
            movie.setReleaseDate(LocalDate.parse(dto.getReleaseDate(), Formatter.dateTimeIndo()));
            movie.setReleaseCountry(dto.getReleaseCountry());

            movieRepository.save(movie);
            return MovieInsertDto.convert(movie);
        }

    }

    @Override
    public MovieUpdateDto updateMovie(String id, MovieUpdateDto movie) {
        Movie movieToUpdate = movieRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        movieToUpdate.setTitle(movie.getTitle());
        movieToUpdate.setProductionYear(movie.getProductionYear());
        movieToUpdate.setDuration(movie.getDuration());
        movieToUpdate.setLanguage(movie.getLanguage());
        movieToUpdate.setReleaseDate(LocalDate.parse(movie.getReleaseDate(), Formatter.dateTimeIndo()));
        movieToUpdate.setReleaseCountry(movie.getReleaseCountry());

        movieRepository.save(movieToUpdate);
        return MovieUpdateDto.convert(movieToUpdate);
    }

    @Override
    public void deleteMovie(String id) {
        boolean cek = movieRepository.findById(id).isPresent();
        if (cek) {
            movieRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException(String.format("Movie with id %s not found", id));
        }
    }


    @Override
    public MovieHeaderDto addActorToMovie(String movieId, Integer actorId, String role) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new EntityNotFoundException("Actor not found"));

        actor.getMovies().add(movie);
        movie.getActors().add(actor);
        movieRepository.save(movie);
        try {
            movieRepository.addRoleToMovieCast(movieId, actorId, role);
        } catch (Exception ignored) {}

        return new MovieHeaderDto(
                movie.getId(),
                movie.getTitle(),
                movie.getProductionYear(),
                movie.getDuration(),
                movie.getLanguage(),
                movie.getReleaseDate(),
                movie.getReleaseCountry()
        );
    }

    @Override
    public Object removeActorFromMovie(String movieId, Integer actorId) {
        Movie movie = movieRepository.findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        Actor actor = actorRepository.findById(actorId).
                orElseThrow(() -> new EntityNotFoundException("Actor not found"));

        actor.getMovies().remove(movie);
        movie.getActors().remove(actor);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public MovieHeaderDto addGenreToMovie(String movieId, Integer genreId) {
        var movie = movieRepository.findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        var genre = genreRepository.findById(genreId).
                orElseThrow(() -> new EntityNotFoundException("Genre not found"));
        var cek = movie.getGenres().contains(genre);
        if (cek) {
            throw new EntityNotFoundException(String.format("Movie %s already has genre %s", movie.getTitle(), genre.getTitle()));
        } else {
            movie.getGenres().add(genre);
            movieRepository.save(movie);
            return new MovieHeaderDto(movie.getId(),
                    movie.getTitle(),
                    movie.getProductionYear(),
                    movie.getDuration(),
                    movie.getLanguage(),
                    movie.getReleaseDate(),
                    movie.getReleaseCountry()
            );
        }
    }

    @Override
    public Object removeGenreFromMovie(String movieId, Integer genreId) {
        var movie = movieRepository.findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        var genre = genreRepository.findById(genreId).
                orElseThrow(() -> new EntityNotFoundException("Genre not found"));

        movie.getGenres().remove(genre);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public MovieHeaderDto addDirectorToMovie(String movieId, Integer directorId) {
        var movie = movieRepository.findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        var director = directorRepository.findById(directorId).
                orElseThrow(() -> new EntityNotFoundException("Director not found"));
        var cek = movie.getDirectors().contains(director);
        if (cek) {
            throw new EntityNotFoundException(String.format("Movie %s already has director %s", movie.getTitle(), director.fetchFullname()));
        } else {
            movie.getDirectors().add(director);
            movieRepository.save(movie);
            return new MovieHeaderDto(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getProductionYear(),
                    movie.getDuration(),
                    movie.getLanguage(),
                    movie.getReleaseDate(),
                    movie.getReleaseCountry()
            );
        }
    }

    @Override
    public Object removeDirectorFromMovie(String movieId, Integer directorId) {
        var movie = movieRepository.findById(movieId).
                orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        var director = directorRepository.findById(directorId).
                orElseThrow(() -> new EntityNotFoundException("Director not found"));

        movie.getDirectors().remove(director);
        movieRepository.save(movie);
        return true;
    }

    @Override
    public List<MovieHeaderDto> getMoviesByGenre(String genreTitle) {
        var cek = genreRepository.findByTitle(genreTitle).isPresent();
        if (cek){
            return movieRepository.findMoviesByGenre(genreTitle).
                    orElseThrow(() -> new EntityNotFoundException(String.format("%s does't have any movie list yet", genreTitle)));
        } else {
            throw new EntityNotFoundException(String.format("Genre with title %s not found", genreTitle));
        }
    }
}
