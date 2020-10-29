package com.example.demo.dto;

import com.example.demo.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    private Integer id;
    private String name;
    private List<Trainer> trainerList;
    private List<Trainee> traineeList;

    public GroupEntity toEntity() {
        return new GroupEntity(id,name,
                trainerList.stream().map(trainer -> trainer.toEntity()).collect(Collectors.toList()),
                traineeList.stream().map(trainee -> trainee.toEntity()).collect(Collectors.toList()));
    }
}
