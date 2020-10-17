package com.apress.todo.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
//@NoArgsConstructor
@Table(name = "todos")
public class ToDoEntity {
//    @NotNull
    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid" )

//    private String id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
//    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }

    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }

//    public ToDo(String description) {
//        this();
//        this.description = description;
//    }
//
//    public ToDo(){
//        LocalDateTime date = LocalDateTime.now();
////        this.id = UUID.randomUUID().toString();
//        this.created = date;
//        this.modified = date;
//    }
}
