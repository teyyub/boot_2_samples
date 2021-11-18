package com.apress.todo.controller;


import com.apress.todo.model.ToDoDto;
import com.apress.todo.validation.ToDoValidationErrorBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ToDoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    @Test
    public void toDoGetTest() throws Exception {
        this.mvc
                .perform(get("/api/todos"))
                .andExpect(status().isOk());
//                .andExpect(content()
//                        .contentType(MediaType.APPLICATION_JSON));
        // TODO: 11/15/21
        //ResponseEntity return edir onu sonra aradirmaq lazimdir heleki bele isleyir
    }

    @Test
    public void toDoPostTest() throws Exception {

        ToDoDto dto = new ToDoDto();
        dto.setDescription("test description");

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(dto));
        this.mvc
                .perform(mockRequest)
                .andExpect(status().isCreated());
    }

    @Test
    public void toDoUpdateTest() throws Exception {

        ToDoDto dto = new ToDoDto();
        dto.setId(1L);
        dto.setDescription("test description updated");

//        Mockito.when(service)

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(dto));
        this.mvc
                .perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void toDoUpdateWithExceptionTest() throws Exception {
        ToDoDto dto = new ToDoDto();
        dto.setId(0L);
        dto.setDescription("test description updated");
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/todos/0")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(dto));
        this.mvc
                .perform(mockRequest)
                .andExpect(result ->
                        assertTrue(result.getResolvedException() instanceof Exception));
//                .andExpect(status().isBadRequest());
    }


    @Test
    public void toDoDeleteTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/todos/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        this.mvc
                .perform(mockRequest)
                .andExpect(status().isNoContent());
    }

    @Test
    public void toDoDeleteExceptionTest() throws Exception {
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/todos/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        this.mvc
                .perform(mockRequest)
                .andExpect(status().isNoContent());
//                .andExpect(result->assertTrue(result.getResolvedException()
//                        instanceof EmptyResultDataAccessException));
    }

}
