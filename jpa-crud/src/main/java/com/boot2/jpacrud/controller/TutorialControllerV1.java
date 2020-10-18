package com.boot2.jpacrud.controller;


import com.boot2.jpacrud.entity.Tutorial;
import com.boot2.jpacrud.exception.ResourceNotFoundException;
import com.boot2.jpacrud.exception.UniqueException;
import com.boot2.jpacrud.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//bu varianta try catch ler yoxdur
//https://bezkoder.com/spring-boot-controlleradvice-exceptionhandler/
@RestController
@RequestMapping("/api")
public class TutorialControllerV1 {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/v1/tutorials")

    //@RequestParam(required = false) String title bunu arasdirmaq lazimdir

    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {

            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title == null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);

    }

    @GetMapping("v1//tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
//        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
//
//        if (tutorialData.isPresent()) {
//            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        Tutorial  tutorialData = tutorialRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("This item not found"));


//            return new ResponseEntity<>(tutorialData,HttpStatus.OK);
        return ResponseEntity.ok(tutorialData);
    }

    @PostMapping("/v1/tutorials")
    public ResponseEntity<?> createTutorial(@RequestBody Tutorial tutorial) {
//               Tutorial  ch =  tutorialRepository.findByDescription(tutorial.getDescription()).orElseThrow(()->new UniqueException("This item already exists in database"));

//              if(tutorialRepository.findOne(tutorial.getDescription())){
//                  throw  new UniqueException("Item already exists");
//              }

        Optional<Tutorial>  ch =  tutorialRepository.findByDescription(tutorial.getDescription());
        if(ch.isPresent()){
            throw  new UniqueException("This item already exists in database");
        }
              Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);

    }

    @PutMapping("/v1/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
        Tutorial  _tutorial = tutorialRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item not found"));
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
    }

    @DeleteMapping("/v1/tutorials/{id}")
    public ResponseEntity<?> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/v1/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/v1/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished() {
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}