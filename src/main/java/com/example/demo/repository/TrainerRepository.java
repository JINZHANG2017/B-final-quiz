package com.example.demo.repository;

import com.example.demo.dto.Trainer;
import com.example.demo.entity.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainerRepository extends JpaRepository<TrainerEntity,Integer> {
    List<TrainerEntity> findAllByGroupIdNotNull();

    List<TrainerEntity> findAllByGroupIdIsNull();
}
