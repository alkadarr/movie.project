package com.Movies.rest_controller;

import com.Movies.dtos.director.DirectorInsertDto;
import com.Movies.dtos.director.DirectorUpdateDto;
import com.Movies.services.abstraction.CrudService;
import com.Movies.services.abstraction.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/director")
public class DirectorRestController {

    @Qualifier("directorMenu")
    @Autowired
    private CrudService crudService;
    @Autowired
    private DirectorService directorService;

    @RequestMapping("/all")
    public Object getAll() {
        return crudService.getAll();
    }

    @RequestMapping("/get/{id}")
    public Object getById(@PathVariable Integer id) {
        return crudService.getById(id);
    }

    @RequestMapping("/insert")
    public Object insert(@RequestBody DirectorInsertDto object) {
        return crudService.insert(object);
    }

    @RequestMapping("/update/{id}")
    public Object update(@PathVariable Integer id,@RequestBody DirectorUpdateDto object) {
        return crudService.update(id, object);
    }

    @RequestMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        crudService.delete(id);
    }

}
