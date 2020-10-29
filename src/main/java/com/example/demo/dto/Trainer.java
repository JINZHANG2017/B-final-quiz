package com.example.demo.dto;

import com.example.demo.entity.TrainerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer {
    private Integer id;
    @NotNull
    private String name;

    public TrainerEntity toEntity() {
        return new TrainerEntity(id, name, null);
    }
}
