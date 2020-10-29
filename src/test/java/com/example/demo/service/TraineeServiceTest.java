package com.example.demo.service;

import com.example.demo.dto.Trainee;
import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraineeServiceTest {
    @Autowired
    private TraineeService traineeService;

    @Mock
    private TraineeRepository traineeRepository;

    private List<TraineeEntity> groupedTraineeList;
    private List<TraineeEntity> ungroupedTraineeList;

    @BeforeEach
    void setUp() {
        traineeService = new TraineeService(traineeRepository);
        GroupEntity groupEntity = new GroupEntity(1, "group1",null,null);
        TraineeEntity groupedTrainee1 = new TraineeEntity(1, "name1", groupEntity);
        TraineeEntity groupedTrainee2 = new TraineeEntity(2, "name2", groupEntity);
        groupedTraineeList = new ArrayList<>();
        groupedTraineeList.add(groupedTrainee1);
        groupedTraineeList.add(groupedTrainee2);
        TraineeEntity ungroupedTrainee1 = new TraineeEntity(3, "name3", null);
        TraineeEntity ungroupedTrainee2 = new TraineeEntity(4, "name4", null);
        ungroupedTraineeList = new ArrayList<>();
        ungroupedTraineeList.add(ungroupedTrainee1);
        ungroupedTraineeList.add(ungroupedTrainee2);
    }

    @Test
    void should_get_grouped_trainee_list() {
        when(traineeRepository.findAllByGroupIdNotNull()).thenReturn(groupedTraineeList);
        List<Trainee> traineeList = traineeService.findAll(true);
        assertThat(traineeList.size()).isEqualTo(2);
        assertThat(traineeList.get(0).getName()).isEqualTo("name1");
        assertThat(traineeList.get(1).getName()).isEqualTo("name2");
    }

    @Test
    void should_get_ungrouped_trainee_list() {
        when(traineeRepository.findAllByGroupIdIsNull()).thenReturn(ungroupedTraineeList);
        List<Trainee> traineeList = traineeService.findAll(false);
        assertThat(traineeList.size()).isEqualTo(2);
        assertThat(traineeList.get(0).getName()).isEqualTo("name3");
        assertThat(traineeList.get(1).getName()).isEqualTo("name4");
    }
}