package com.example.demo.api;

import com.example.demo.dto.Trainee;
import com.example.demo.service.TraineeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TraineeController.class)
@AutoConfigureJsonTesters
class TraineeControllerTest {
    @MockBean
    private TraineeService traineeService;
    @Autowired
    private MockMvc mockMvc;
//    @Autowired
//    private JacksonTester<Trainee> traineeJson;

    private List<Trainee> groupedTraineeList;
    private List<Trainee> ungroupedTraineeList;

    @BeforeEach
    void setUp() {
        Trainee groupedTrainee1=new Trainee(1,"name1");
        Trainee groupedTrainee2=new Trainee(2,"name2");
        groupedTraineeList=new ArrayList<>();
        groupedTraineeList.add(groupedTrainee1);
        groupedTraineeList.add(groupedTrainee2);
        Trainee ungroupedTrainee1=new Trainee(3,"name3");
        Trainee ungroupedTrainee2=new Trainee(4,"name4");
        ungroupedTraineeList=new ArrayList<>();
        ungroupedTraineeList.add(ungroupedTrainee1);
        ungroupedTraineeList.add(ungroupedTrainee2);
    }

    @Test
    public void should_get_grouped_trainee() throws Exception {
        when(traineeService.findAll(true)).thenReturn(groupedTraineeList);
        mockMvc.perform(get("/trainees?grouped=true",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("name1")))
                .andExpect(jsonPath("$[1].name", is("name2")));
    }

    @Test
    public void should_get_ungrouped_trainee() throws Exception {
        when(traineeService.findAll(false)).thenReturn(ungroupedTraineeList);
        mockMvc.perform(get("/trainees?grouped=false",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("name3")))
                .andExpect(jsonPath("$[1].name", is("name4")));
    }
}