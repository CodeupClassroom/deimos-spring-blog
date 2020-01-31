package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Task;
import com.codeup.deimosspringblog.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.PastOrPresent;

@Controller
public class ToDoController {

    private TaskRepository taskDao;

    public ToDoController(TaskRepository taskDao){ this.taskDao = taskDao; }

    @GetMapping("/todo")
    public String index(Model model){
        model.addAttribute("tasks", taskDao.findAll());
        return "todo/index";
    }

    @GetMapping("/todo/{id}/edit")
    public String editForm(@PathVariable long id,Model model){
        model.addAttribute("task", taskDao.getOne(id));
        return "todo/edit";
    }

    @PostMapping("/todo/{id}/edit")
    public String updateTask(@PathVariable long id,
                             @ModelAttribute Task task){
        taskDao.save(task);
        return "redirect:/todo";
    }

    @GetMapping("/todo/{id}/delete")
    public String deleteTask(@PathVariable long id){
        taskDao.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/todo/create")
    public String createTaskForm(Model model){
        model.addAttribute("task",new Task());
        return "todo/create";
    }

    @PostMapping("/todo/create")
    public String createTask(@ModelAttribute Task task){
        taskDao.save(task);
        return "redirect:/todo";
    }
}
