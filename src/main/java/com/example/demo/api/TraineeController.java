package com.example.demo.api;


import com.example.demo.dto.Trainee;
import com.example.demo.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trainees")
public class TraineeController {
//    GET /trainees?grouped=false

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    public List<Trainee> getList(@RequestParam Boolean grouped){
        return traineeService.findAll(grouped);
    }

}
