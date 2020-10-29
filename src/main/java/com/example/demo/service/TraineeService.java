package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> findAll(Boolean grouped) {
//        trainerRepository.findAll
        List<TraineeEntity> traineeEntityList = new ArrayList<>();
        if (grouped) {
            traineeEntityList =traineeRepository.findAllByGroupIdNotNull();
        }else{
            traineeEntityList =traineeRepository.findAllByGroupIdIsNull();
        }
        return traineeEntityList.stream().map( traineeEntity-> {
            return traineeEntity.toDto();
        }).collect(Collectors.toList());
    }

    public Trainee add(Trainee trainee) {
        TraineeEntity entity = trainee.toEntity();
        traineeRepository.save(entity);
        return entity.toDto();
    }
}
