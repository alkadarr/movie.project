package com.Movies.rest_controller;

import com.Movies.dtos.actor.ActorUpsertDto;
import com.Movies.services.abstraction.ActorService;
import com.Movies.services.abstraction.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actor")
public class ActorRestController {

    @Qualifier("actorMenu")
    @Autowired
    private CrudService crudService;

    @Autowired
    private ActorService actorService;

    @GetMapping("/all")
    public List<?> getAllActors() {
        return crudService.getAll();
    }

    @GetMapping("/get/{id}")
    public Object getActorById(@PathVariable Integer id) {
        return crudService.getById(id);
    }

    @PostMapping("/insert")
    public Object insertActor(@RequestBody ActorUpsertDto actor) {
        return crudService.insert(actor);
    }

    @PutMapping("/update/{id}")
    public Object updateActor(@PathVariable Integer id,@RequestBody ActorUpsertDto actor) {
        return crudService.update(id, actor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteActor(@PathVariable Integer id) {
        crudService.delete(id);
    }
}
