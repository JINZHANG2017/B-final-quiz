package com.example.demo.entity;

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

//    @OneToMany(cascade = CascadeType, mappedBy = "group")
//    private List<TrainerEntity> trainerList;
//
//    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group")
//    private List<TraineeEntity> traineeList;
}
