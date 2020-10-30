package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> findAll(Boolean grouped) {
        // TODO GTB-工程实践: - 不用的代码应该及时清理
//        trainerRepository.findAll
        List<TraineeEntity> traineeEntityList = new ArrayList<>();
        if (grouped) {
            traineeEntityList = traineeRepository.findAllByGroupIdNotNull();
        } else {
            traineeEntityList = traineeRepository.findAllByGroupIdIsNull();
        }
        // TODO GTB-知识点: - 以下lambda表达式可以简化
        return traineeEntityList.stream().map(traineeEntity -> {
            return traineeEntity.toDto();
        }).collect(Collectors.toList());
    }

    public Trainee add(Trainee trainee) {
        TraineeEntity entity = trainee.toEntity();
        traineeRepository.save(entity);
        return entity.toDto();
    }

    public void delete(Integer id) {
        Optional<TraineeEntity> traineeEntity = traineeRepository.findById(id);
        // TODO GTB-知识点: - 可以使用orElseThrow方法
        if (traineeEntity.isPresent()) {
            traineeRepository.delete(traineeEntity.get());
        } else {
            throw new ResourceNotFoundException("未找到学员！");
        }
    }
}
