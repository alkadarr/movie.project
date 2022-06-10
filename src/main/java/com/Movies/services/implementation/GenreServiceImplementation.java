package com.Movies.services.implementation;

import com.Movies.dtos.genre.GenreHeaderDto;
import com.Movies.dtos.genre.GenreInsertDto;
import com.Movies.dtos.genre.GenreUpdateDto;
import com.Movies.models.Genre;
import com.Movies.repositories.GenreRepository;
import com.Movies.services.abstraction.CrudService;
import com.Movies.services.abstraction.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service("genreMenu")
public class GenreServiceImplementation implements CrudService, GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<?> getAll() {
        return genreRepository.findAll().stream().
                map(genre -> new GenreHeaderDto(
                        genre.getId(),
                        genre.getTitle())).
                collect(Collectors.toList());
    }

    @Override
    public Object getById(Object id) {
        return genreRepository.findById((Integer) id)
                .map(genre -> new GenreHeaderDto(
                        genre.getId(),
                        genre.getTitle()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Genre with id : %s not found", id)));
    }

    @Override
    public Object insert(Object object) {
        boolean cek = genreRepository.findByTitle(((GenreInsertDto) object).getTitle()).isPresent();
        if (cek) {
            throw new EntityNotFoundException(String.format("Genre with title : %s already exists", ((GenreInsertDto) object).getTitle()));
        } else {
            Genre newGenre = new Genre();
            newGenre.setTitle(((GenreInsertDto) object).getTitle());

            genreRepository.save(newGenre);

            return new GenreHeaderDto(
                    newGenre.getId(),
                    newGenre.getTitle());
        }
    }

    @Override
    public Object update(Object id, Object object) {
        Genre oldGenre = genreRepository.findById((Integer) id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Genre with id : %s not found", id)));

        boolean cek = genreRepository.findByTitle(((GenreUpdateDto) object).getTitle()).isPresent();
        if (cek) {
            throw new EntityNotFoundException(String.format("Genre with title : %s already exists", ((GenreUpdateDto) object).getTitle()));
        } else {
            oldGenre.setTitle(((GenreUpdateDto) object).getTitle());

            genreRepository.save(oldGenre);

            return new GenreUpdateDto(
                    oldGenre.getId(),
                    oldGenre.getTitle());
        }
    }

    @Override
    public void delete(Object id) {
        boolean cek = genreRepository.findById((Integer) id).isPresent();
        if (cek) {
            genreRepository.deleteById((Integer) id);
        } else {
            throw new EntityNotFoundException(String.format("Genre with id : %s not found", id));
        }
    }
}
