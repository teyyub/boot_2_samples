package com.boot2.controller;

import com.boot2.domain.ToDo;
import com.boot2.repository.CommonRepository;
import com.boot2.domain.ToDoBuilder;
import com.boot2.validation.ToDoValidationError;
import com.boot2.validation.ToDoValidationErrorBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated // bunu arasdirmaq
public class ToDoController {

    private CommonRepository<ToDo> repository;

    @Autowired
    public ToDoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    @GetMapping("/todos") //@RequestMapping(value="/todo", method = {RequestMethod.GET})
    public ResponseEntity<Iterable<ToDo>> getToDos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable String id){
        return ResponseEntity.ok(repository.findById(id));
    }

    @PatchMapping("/todos/{id}")
    public ResponseEntity<ToDo> setCompleted(@PathVariable String id){
        ToDo result = repository.findById(id);
        System.out.println("result ===== " +result.toString());
        result.setCompleted(true);
        repository.save(result);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.ok().header("Location",location.toString()).build();
    }

//   @RequestMapping(value="/todos", method = {RequestMethod.POST, RequestMethod.PUT})
//   public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors){
//        if (errors.hasErrors()) {
//            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
//        }
//
//        ToDo result = repository.save(toDo);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(result.getId()).toUri();
//        //result da header de location da gosterir
////        return ResponseEntity.created(location).build();
//        return ResponseEntity.ok(result);
//    }

    @RequestMapping(value="/todos", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo ){
//        if (errors.hasErrors()) {
//            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
//        }

        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        //result da header de location da gosterir
//        return ResponseEntity.created(location).build();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id){
        repository.delete(ToDoBuilder.create().withId(id).build());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todos")
    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
        repository.delete(toDo);
        return ResponseEntity.noContent().build();
    }

//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ToDoValidationError handleException(Exception exception) {
//        return new ToDoValidationError(exception.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

// TODO: 11/13/21
  //https://www.bezkoder.com/spring-boot-controlleradvice-exceptionhandler/
  //bunlar arasinda ferqler

//    https://www.baeldung.com/spring-boot-bean-validation
//   object invalid olanda MethodArgumentNotValidException bunu tutmaq lazimdir
}
