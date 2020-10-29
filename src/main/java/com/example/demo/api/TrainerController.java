package com.example.demo.api;

import com.example.demo.dto.Trainer;
import com.example.demo.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trainers")
public class TrainerController {

    private final
    TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public List<Trainer> getGroupedTrainers(@PathVariable(value = "grouped") Boolean grouped) {
        return trainerService.getTrainers(grouped);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTrainer(@RequestBody @Valid Trainer trainer) {
        trainerService.add(trainer);
    }

    @DeleteMapping("/{trainer_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable(value = "trainer_id") Integer id) {
        trainerService.delete(id);
    }


}
