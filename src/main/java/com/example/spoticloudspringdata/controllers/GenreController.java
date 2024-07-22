package com.example.spoticloudspringdata.controllers;

import com.example.spoticloudspringdata.entities.Genre;
import com.example.spoticloudspringdata.schemas.GenreDto;
import com.example.spoticloudspringdata.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }



//    @GetMapping
//    public Map<String, String> getAll() {
//        Map<String, String> response = new HashMap<>();
//
//        List<Genre> genres = genreService.getAllGenres();
//        for (Genre genre : genres) {
//            if (genre.getParentGenre() != null) {
//                response.put(genre.getName(), genre.getParentGenre().getName());
//            } else {
//                response.put(genre.getName(), "null");
//            }        }
//        return response;
//    }
    @GetMapping
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    @GetMapping("/{name}")
    public Genre getGenre(@PathVariable String name) {
        return genreService.findByName(name);
    }
}
