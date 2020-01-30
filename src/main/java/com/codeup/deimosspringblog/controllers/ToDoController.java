package com.codeup.deimosspringblog.controllers;

import com.codeup.deimosspringblog.models.Task;
import com.codeup.deimosspringblog.repositories.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
                             @RequestParam String title,
                             @RequestParam String date,
                             @RequestParam String time,
                             @RequestParam String notes){
        Task task = taskDao.getOne(id);
        task.setTitle(title);
        task.setDate(date);
        task.setTime(time);
        task.setNotes(notes);
        taskDao.save(task);
        return "redirect:/todo";
    }

    @GetMapping("/todo/{id}/delete")
    public String deleteTask(@PathVariable long id){
        taskDao.deleteById(id);
        return "redirect:/todo";
    }

    @GetMapping("/todo/create")
    public String createTaskForm(){
        return "todo/create";
    }

    @PostMapping("/todo/create")
    public String createTask(@RequestParam String title,
                             @RequestParam String date,
                             @RequestParam String time,
                             @RequestParam String notes){
        Task task = new Task(title, date, time, notes);
        taskDao.save(task);
        return "redirect:/todo";
    }
}
