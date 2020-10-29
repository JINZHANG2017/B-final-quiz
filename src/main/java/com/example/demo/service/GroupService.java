package com.example.demo.service;

import com.example.demo.dto.Group;
import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import com.example.demo.entity.GroupEntity;
import com.example.demo.entity.TraineeEntity;
import com.example.demo.entity.TrainerEntity;
import com.example.demo.repository.GroupRepostitory;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepostitory groupRepostitory;
    private final TrainerRepository trainerRepository;
    private final TraineeRepository traineeRepository;

    public GroupService(GroupRepostitory groupRepostitory, TrainerRepository trainerRepository, TraineeRepository traineeRepository) {
        this.groupRepostitory = groupRepostitory;
        this.trainerRepository = trainerRepository;
        this.traineeRepository = traineeRepository;
    }

    public List<Group> getGroupList() {
        List<GroupEntity> groupEntities = groupRepostitory.findAll();
        List<Group> groupList = groupEntities.stream().map(groupEntity -> groupEntity.toDto()).collect(Collectors.toList());
        return groupList;
    }

    public List<Group> group() {
        groupRepostitory.deleteAll();
        List<TraineeEntity> traineeEntityList = traineeRepository.findAll();
        List<TrainerEntity> trainerEntityList = trainerRepository.findAll();
        int groupNum = trainerEntityList.size() / 2;
        Collections.shuffle(trainerEntityList);
        Collections.shuffle(traineeEntityList);

        List<List<Trainee>> traineeList = new ArrayList<>();
        List<List<Trainer>> trainerList = new ArrayList<>();
        for (int i = 0; i < groupNum; i++) {
            traineeList.add(new ArrayList<>());
            trainerList.add(new ArrayList<>());
            trainerList.get(i).add(trainerEntityList.get(2 * i).toDto());
            trainerList.get(i).add(trainerEntityList.get(2 * i + 1).toDto());
        }
        for (int i = 0; i < traineeEntityList.size(); i++) {
            int groupIndex = i % groupNum;
            traineeList.get(groupIndex).add(traineeEntityList.get(i).toDto());
        }
        List<Group> list = new ArrayList<>();
        for (int i = 1; i <= groupNum; i++) {
//            Team 1
            Group group = new Group(i, "Team " + i, trainerList.get(i - 1), traineeList.get(i - 1));
            GroupEntity groupEntity=group.toEntity();
            groupRepostitory.save(groupEntity);
            list.add(group);
        }
        return list;
    }
}
