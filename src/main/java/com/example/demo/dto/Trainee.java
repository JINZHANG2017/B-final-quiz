package com.example.demo.dto;

import com.example.demo.entity.TraineeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainee {
    private Integer id;
    private String name;

    public TraineeEntity toEntity() {
        return new TraineeEntity(id,name,null);
    }
}
