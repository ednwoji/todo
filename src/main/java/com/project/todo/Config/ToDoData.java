package com.project.todo.Config;

import com.project.todo.Entity.Todo;
import com.project.todo.Repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class ToDoData implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(ToDoData.class);
    @Autowired
    TodoRepository todoRepository;
    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    public void loadData() {

        if(todoRepository.count()==0) {

            Todo todoitem1 = new Todo("Walk to the Office");
            Todo todoitem2 = new Todo("Take a cup of coffee");

            todoRepository.save(todoitem1);
            todoRepository.save(todoitem2);
            logger.info("Number of TodoItems: {}", todoRepository.count());
        }


    }
}
