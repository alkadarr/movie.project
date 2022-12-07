package com.Movies.rest_controller;

import com.Movies.dtos.reviewer.CompleteProfileDto;
import com.Movies.services.abstraction.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/reviewer")
public class ReviewerRestController {

    @Autowired
    private ReviewerService service;

    @GetMapping("/all")
    public List<?> getAll() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Object getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public Object delete(@PathVariable Long id) {
        return service.delete(id) ? "Deleted" : "Not deleted";
    }

//    @PostMapping("/complete-profile")
//    public Object completeProfile(Authentication auth, @RequestBody CompleteProfileDto dto) {
//        return service.completeProfile(auth.getName(), dto);
//    }

//    @PutMapping("/rate-movie")
//    public Object addRatingToMovie(Authentication auth, @RequestParam String movieId, @RequestParam Integer stars) {
//        return service.addRatingToMovie(auth.getName(), movieId, stars);
//    }


}
