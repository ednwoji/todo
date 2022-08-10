package com.project.todo.Controller;

import com.project.todo.Entity.Todo;
import com.project.todo.Repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@ComponentScan
public class ToDoFormController {

    private final Logger logger = LoggerFactory.getLogger(ToDoFormController.class);

    @Autowired
    private TodoRepository todoRepository;


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + " not found"));

        model.addAttribute("todo", todo);
        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo id: " + id + " not found"));

        todoRepository.delete(todo);
        return "redirect:/";
    }

    @GetMapping("/create-todo")
    public String showCreateForm(Todo todo){
        return "add-todo-item";
    }


}
