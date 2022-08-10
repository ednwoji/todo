package com.project.todo.Controller;


import com.project.todo.Entity.Todo;
import com.project.todo.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;

@Controller
@ComponentScan
public class ToDoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("Todo", todoRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            todo.setId(id);
            return "update-todo-item";
        }

        todo.setModifiedTime(Instant.now());
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid Todo todo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-todo-item";
        }

        todo.setCreatedTime(Instant.now());
        todo.setModifiedTime(Instant.now());
        todoRepository.save(todo);
        return "redirect:/";
    }
}
