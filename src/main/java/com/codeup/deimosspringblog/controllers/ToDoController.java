package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToDoController {

    private TaskRepository taskDao;

    public ToDoController(TaskRepository taskDao){ this.taskDao = taskDao; }

    @GetMapping("/todo")
    public String index(Model model){
        model.addAttribute("tasks", taskDao.findAll());
        return "todo/index";
    }
}
