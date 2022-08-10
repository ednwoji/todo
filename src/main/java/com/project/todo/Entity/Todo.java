package com.project.todo.Entity;


import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
public class Todo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;


    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Instant createdTime;

    @Getter
    @Setter
    private Instant modifiedTime;

    public Todo() {
    }

    public Todo(String description) {

        this.description = description;
        this.complete = false;
        this.createdTime = Instant.now();
        this.modifiedTime = Instant.now();
    }

    @Override
    public String toString() {
        return String.format("TodoItem{id=%d, description='%s', complete='%s', createdDate='%s', modifiedDate='%s'}",
                id, description, complete, createdTime, modifiedTime);
    }

}
