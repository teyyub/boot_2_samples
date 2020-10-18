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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class ToDoController {

    private CommonRepository<ToDo> repository;

    @Autowired
    public ToDoController(CommonRepository<ToDo> repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<ToDo>> getToDos(){
//        return ResponseEntity.ok(repository.findAll());
//        return new ResponseEntity(repository.findAll(),HttpStatus.OK);
        return new ResponseEntity("necesen",HttpStatus.OK);
    }

    //response entity olmadan yoxlamaq isteyirem
    @GetMapping("/v1/todos")
    public Iterable<ToDo> getToDosV1(){
        return repository.findAll();
    }


    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getToDoById(@PathVariable String id){
        System.out.println("gelen id " +id);
        ToDo resul =  repository.findById(id);
        System.out.println("result="+resul);
        if(resul==null){
            return ResponseEntity.ok().body("There is no item");
        }
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping("/v1/todos/{id}")
    public ResponseEntity<?> getToDoByIdV1(@PathVariable String id){
        System.out.println("v1/todos/id " +id);
        ToDo resul =  repository.findById(id);
        System.out.println("result="+resul);
        return ResponseEntity.ok(repository.findById(id));
    }

    @GetMapping("/v2/todos/{id}")
    public ToDo getToDoByIdV2(@PathVariable String id){
        System.out.println("v2/todos/id " +id);
        ToDo resul =  repository.findById(id);
        System.out.println("result="+resul);
        return repository.findById(id);
    }

    @GetMapping("/v3/todos/{id}")
    public ResponseEntity<?> getToDoByIdV3(@PathVariable String id){
        System.out.println("gelen id " +id);
        ToDo resul =  repository.findById(id);
        System.out.println("result="+resul);
        if(resul==null){
            return ResponseEntity.noContent().build();
        }
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

    @RequestMapping(value="/todos", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo, Errors errors){
        System.out.println(" createToDo errors " + errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }

        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    //@Valid
    @RequestMapping(value="/v2/todos", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createToDoV2(@Valid @RequestBody ToDo toDo, Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }

        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    //valid goturuldu
    @RequestMapping(value="/v1/todos", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> createToDoV1(@RequestBody ToDo toDo, Errors errors){
        System.out.println("createToDoV1 errors " +errors);
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }

        ToDo result = repository.save(toDo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
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

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ToDoValidationError handleException(Exception exception) {
        System.out.println("handle exception " +exception);
        return new ToDoValidationError(exception.getMessage());
    }

}
