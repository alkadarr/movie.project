package com.Movies.rest_controller;

import com.Movies.dtos.genre.GenreHeaderDto;
import com.Movies.dtos.genre.GenreInsertDto;
import com.Movies.dtos.genre.GenreUpdateDto;
import com.Movies.services.abstraction.CrudService;
import com.Movies.services.abstraction.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/genre")
public class GenreRestController {

    @Qualifier("genreMenu")
    @Autowired
    private CrudService crudService;

    @Autowired
    private GenreService genreService;

    @GetMapping("/all")
    public List<?> getAllGenres() {
        return crudService.getAll();
    }

    @GetMapping("/get/{id}")
    public GenreHeaderDto getGenreById(@PathVariable Integer id) {
        return (GenreHeaderDto) crudService.getById(id);
    }

    @PostMapping("/insert")
    public GenreHeaderDto insertGenre(@RequestBody GenreInsertDto genre) {
        return (GenreHeaderDto) crudService.insert(genre);
    }

    @PutMapping("/update/{id}")
    public GenreUpdateDto updateGenre(@PathVariable Integer id, @RequestBody GenreUpdateDto genre) {
        return (GenreUpdateDto) crudService.update(id, genre);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGenre(@PathVariable Integer id) {
        crudService.delete(id);
    }
}
