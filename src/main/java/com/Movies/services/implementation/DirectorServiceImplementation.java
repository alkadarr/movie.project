package com.Movies.services.implementation;

import com.Movies.dtos.director.DirectorHeaderDto;
import com.Movies.dtos.director.DirectorInsertDto;
import com.Movies.dtos.director.DirectorUpdateDto;
import com.Movies.models.Director;
import com.Movies.repositories.DirectorRepository;
import com.Movies.services.abstraction.CrudService;
import com.Movies.services.abstraction.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service("directorMenu")
public class DirectorServiceImplementation implements CrudService, DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public List<?> getAll() {
        return directorRepository.findAll().stream().
                map(director -> new DirectorHeaderDto(
                        director.getId(),
                        director.getFirstName(),
                        director.getLastName())).
                collect(Collectors.toList());
    }

    @Override
    public Object getById(Object id) {
        return directorRepository.findById((Integer) id)
                .map(director -> new DirectorHeaderDto(
                        director.getId(),
                        director.getFirstName(),
                        director.getLastName()))
                .orElseThrow(() -> new EntityNotFoundException(String.format("Director with id : %s not found", id)));
    }

    @Override
    public Object insert(Object object) {
        boolean cek = directorRepository.findByFirstNameAndLastName(
                ((DirectorInsertDto) object).getFirstName(),
                ((DirectorInsertDto) object).getLastName()).isPresent();
        if (cek) {
            throw new EntityNotFoundException(String.format("Director with first name : %s, and last name : %s already exists",
                    ((DirectorInsertDto) object).getFirstName(),
                    ((DirectorInsertDto) object).getLastName()));
        } else {
            Director newDirector = new Director();
            newDirector.setFirstName(((DirectorInsertDto) object).getFirstName());
            newDirector.setLastName(((DirectorInsertDto) object).getLastName());

            directorRepository.save(newDirector);

            return new DirectorHeaderDto(
                    newDirector.getId(),
                    newDirector.getFirstName(),
                    newDirector.getLastName()
            );
        }
    }

    @Override
    public Object update(Object id, Object object) {
        var oldDirector = directorRepository.findById((Integer) id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Director with id : %s not found", id)));

        boolean cek = directorRepository.findByFirstNameAndLastName(
                ((DirectorUpdateDto) object).getFirstName(),
                ((DirectorUpdateDto) object).getLastName()).isPresent();
        if (cek) {
            throw new EntityNotFoundException(String.format("Director with first name : %s, and last name : %s already exists",
                    ((DirectorUpdateDto) object).getFirstName(),
                    ((DirectorUpdateDto) object).getLastName()));
        } else {
            oldDirector.setFirstName(((DirectorUpdateDto) object).getFirstName());
            oldDirector.setLastName(((DirectorUpdateDto) object).getLastName());

            directorRepository.save(oldDirector);

            return new DirectorUpdateDto(
                    oldDirector.getId(),
                    oldDirector.getFirstName(),
                    oldDirector.getLastName()
            );
        }
    }

    @Override
    public void delete(Object id) {
        boolean cek = directorRepository.findById((Integer) id).isPresent();
        if (cek) {
            directorRepository.deleteById((Integer) id);
        } else {
            throw new EntityNotFoundException(String.format("Director with id : %s not found", id));
        }
    }
}
