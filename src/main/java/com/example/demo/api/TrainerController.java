package com.example.demo.api;

import com.example.demo.dto.Trainer;
import com.example.demo.service.TraineeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

//    TraineeService Traine
    @GetMapping
    public List<Trainer> getGroupedTrainers(){
        return null;
    }
}
