package com.Movies.services.implementation;

import com.Movies.dtos.actor.ActorHeaderDto;
import com.Movies.dtos.actor.ActorUpsertDto;
import com.Movies.models.Actor;
import com.Movies.repositories.ActorRepository;
import com.Movies.services.abstraction.ActorService;
import com.Movies.services.abstraction.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service("actorMenu")
public class ActorServiceImplementation implements CrudService,ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public List<?> getAll() {
        return actorRepository.findAll().stream().
                map(actor -> new ActorHeaderDto(
                        actor.getId(),
                        actor.getFirstName(),
                        actor.getLastName(),
                        actor.getGender())).
                collect(Collectors.toList());
    }

    @Override
    public Object getById(Object id) {
        return actorRepository.findById((Integer) id)
                .map(actor -> new ActorHeaderDto(
                        actor.getId(),
                        actor.getFirstName(),
                        actor.getLastName(),
                        actor.getGender()))
                .orElseThrow(() -> new EntityNotFoundException("Actor not found"));
    }

    @Override
    public Object insert(Object actor) {
        boolean cek = actorRepository.findByFirstNameAndLastNameAndGender(((ActorUpsertDto) actor).getFirstName(), ((ActorUpsertDto) actor).getLastName(), ((ActorUpsertDto) actor).getGender()).isPresent();
        if (cek) {
            throw new EntityNotFoundException(String.format("Actor with first name : %s, last name : %s, and gender : %s already exists", ((ActorUpsertDto) actor).getFirstName(), ((ActorUpsertDto) actor).getLastName(),((ActorUpsertDto) actor).getGender()));
        } else {
            Actor newActor = new Actor();
            newActor.setFirstName(((ActorUpsertDto) actor).getFirstName());
            newActor.setLastName(((ActorUpsertDto) actor).getLastName());
            newActor.setGender(((ActorUpsertDto) actor).getGender());

            actorRepository.save(newActor);

            return new ActorUpsertDto(
                    newActor.getId(),
                    newActor.getFirstName(),
                    newActor.getLastName(),
                    newActor.getGender()
            );
        }
    }

    @Override
    public Object update(Object id, Object actor) {
        Actor oldActor = actorRepository.findById((Integer) id).
                orElseThrow(() -> new EntityNotFoundException(String.format("Actor with id %s not found", id)));

        oldActor.setFirstName(((ActorUpsertDto) actor).getFirstName());
        oldActor.setLastName(((ActorUpsertDto) actor).getLastName());
        oldActor.setGender(((ActorUpsertDto) actor).getGender());

        actorRepository.save(oldActor);

        return new ActorUpsertDto(
                oldActor.getId(),
                oldActor.getFirstName(),
                oldActor.getLastName(),
                oldActor.getGender()
        );
    }

    @Override
    public void delete(Object id) {
        boolean cek = actorRepository.findById((Integer) id).isPresent();
        if (cek) {
            actorRepository.deleteById((Integer) id);
        } else {
            throw new EntityNotFoundException(String.format("Actor with id %s not found", id));
        }
    }
}
