package com.apress.todo.controller;

import com.apress.todo.model.ToDoDto;
import com.apress.todo.service.ToDoService;
import com.apress.todo.validation.ToDoValidationErrorBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }
    @GetMapping("")
    public ResponseEntity<?> getToDos(){
        return ResponseEntity.ok(service.findAll());
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getToDoById(@PathVariable Long id){
//        Optional<ToDoDto> toDo = toDoRepository.findById(id);
//        if(toDo.isPresent())
//            return ResponseEntity.ok(toDo.get());
//        return ResponseEntity.notFound().build();
//    }
//    @PatchMapping("/{id}")
//    public ResponseEntity<?> setCompleted(@PathVariable Long id){
//        Optional<ToDoDto> toDo = toDoRepository.findById(id);
//        if(!toDo.isPresent())
//            return ResponseEntity.notFound().build();
////        ToDo result = toDo.get();
////        result.setCompleted(true);
////        toDoRepository.save(result);
////        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
////                .buildAndExpand(result.getId()).toUri();
////        return ResponseEntity.ok().header("Location",location.toString()).
////                build();
//        return null;
//    }
    @RequestMapping(value="", method = {RequestMethod.POST})
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDoDto toDo,
                                        Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDoDto result = service.save(toDo).get();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ToDoDto toDo,
                                    @PathVariable Long id,
                                        Errors errors){
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().
                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
        }
        ToDoDto result = service.update(toDo,id).get();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.ok(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteToDo(@PathVariable String id){
//        toDoRepository.delete(ToDoBuilder.create().withId(id).build());
//        return ResponseEntity.noContent().build();
//    }
//    @DeleteMapping("")
//    public ResponseEntity<?> deleteToDo(@RequestBody ToDoDto toDo){
//        toDoRepository.delete(toDo);
//        return ResponseEntity.noContent().build();
//    }
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ToDoValidationError handleException(Exception exception) {
//        return new ToDoValidationError(exception.getMessage());
//    }
}