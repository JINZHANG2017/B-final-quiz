package com.example.demo.repository;

import com.example.demo.entity.TraineeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TraineeRepository extends JpaRepository<TraineeEntity, Integer> {
    List<TraineeEntity> findAllByGroupIdIsNull();

    List<TraineeEntity> findAllByGroupIdNotNull();
}
