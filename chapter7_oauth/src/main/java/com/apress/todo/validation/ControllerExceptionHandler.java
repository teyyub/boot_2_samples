package com.apress.todo.validation;


//@ControllerAdvice
//@RestController
//@ResponseBody
public class ControllerExceptionHandler {

//    @ExceptionHandler(Exception.class)
////    @ResponseStatus(value = HttpStatus.NOT_FOUND)
//    public ToDoValidationError exception(Exception e,WebRequest request) {
//        ToDoValidationError message = new ToDoValidationError(
//                e.getMessage()
////                request.getDescription(false);
//        );
//        return message;
//    }


//    @ExceptionHandler(Exception.class)
//    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}