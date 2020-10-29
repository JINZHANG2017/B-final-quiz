package com.example.demo.entity;

import com.example.demo.dto.Group;
import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_group")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Group toDto() {
        List<Trainee> traineeDtoList = this.traineeList.stream().map(traineeEntity -> traineeEntity.toDto()).collect(Collectors.toList());
        List<Trainer> trainerDtoList = this.trainerList.stream().map(trainerEntity -> trainerEntity.toDto()).collect(Collectors.toList());
        return new Group(id,name, trainerDtoList, traineeDtoList);
    }

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "group")
    private List<TrainerEntity> trainerList;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "group")
    private List<TraineeEntity> traineeList;
}
