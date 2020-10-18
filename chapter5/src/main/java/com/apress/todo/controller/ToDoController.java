package com.apress.todo.controller;

import com.apress.todo.entity.ToDo;
import com.boot2.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api")
public class ToDoController {
    private ToDoRepository toDoRepository;
    @Autowired
    public ToDoController(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }
    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> getToDos(){
        return ResponseEntity.ok(toDoRepository.findAll());
    }
//    @GetMapping("/todo/{id}")
//    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id){
//        Optional<ToDo> toDo = toDoRepository.findById(id);
//        if(toDo.isPresent())
//            return ResponseEntity.ok(toDo.get());
//        return ResponseEntity.notFound().build();
//    }
//    @PatchMapping("/todo/{id}")
//    public ResponseEntity<ToDo> setCompleted(@PathVariable String id){
//        Optional<ToDo> toDo = toDoRepository.findById(id);
//        if(!toDo.isPresent())
//            return ResponseEntity.notFound().build();
//        ToDo result = toDo.get();
//        result.setCompleted(true);
//        toDoRepository.save(result);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .buildAndExpand(result.getId()).toUri();
//        return ResponseEntity.ok().header("Location",location.toString()).
//                build();
//    }
//    @RequestMapping(value="/todo", method = {RequestMethod.
//            POST,RequestMethod.PUT})
//    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo toDo,
//                                        Errors errors){
//        if (errors.hasErrors()) {
//            return ResponseEntity.badRequest().
//                    body(ToDoValidationErrorBuilder.fromBindingErrors(errors));
//        }
//        ToDo result = toDoRepository.save(toDo);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
//                path("/{id}")
//                .buildAndExpand(result.getId()).toUri();
//        return ResponseEntity.created(location).build();
//    }
//    @DeleteMapping("/todo/{id}")
//    public ResponseEntity<ToDo> deleteToDo(@PathVariable String id){
//        toDoRepository.delete(ToDoBuilder.create().withId(id).build());
//        return ResponseEntity.noContent().build();
//    }
//    @DeleteMapping("/todo")
//    public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
//        toDoRepository.delete(toDo);
//        return ResponseEntity.noContent().build();
//    }
//    @ExceptionHandler
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    public ToDoValidationError handleException(Exception exception) {
//        return new ToDoValidationError(exception.getMessage());
//    }
}