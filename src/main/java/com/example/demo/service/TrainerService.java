package com.example.demo.service;

import com.example.demo.dto.Trainer;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerService(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    public List<Trainer> getTrainers(Boolean grouped) {
        List<TrainerEntity> trainerEntities = new ArrayList<>();
        if (grouped) {
            trainerEntities = trainerRepository.findAllByGroupIdNotNull();
        } else {
            trainerEntities = trainerRepository.findAllByGroupIdIsNull();
        }
        return trainerEntities.stream().map(trainerEntity -> {
            return trainerEntity.toDto();
        }).collect(Collectors.toList());
    }

    public void add(Trainer trainer) {
        trainerRepository.save(trainer.toEntity());
    }

    public void delete(Integer id) {
        Optional<TrainerEntity> trainerEntity = trainerRepository.findById(id);
        if(trainerEntity.isPresent()){
            trainerRepository.delete(trainerEntity.get());
        }else{
            throw new ResourceNotFoundException("未找到导师！");
        }
    }
}
