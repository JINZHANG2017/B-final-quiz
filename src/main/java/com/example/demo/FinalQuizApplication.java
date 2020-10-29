package com.example.demo;

import com.example.demo.dto.Group;
import com.example.demo.dto.Trainee;
import com.example.demo.dto.Trainer;
import com.example.demo.service.GroupService;
import com.example.demo.service.TraineeService;
import com.example.demo.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FinalQuizApplication {

	@Autowired
	GroupService groupService;
	@Autowired
	TraineeService traineeService;
	@Autowired
	TrainerService trainerService;


	public static void main(String[] args) {
		SpringApplication.run(FinalQuizApplication.class, args);

	}

	@PostConstruct
	private void initial(){
		System.out.println(groupService.getGroupList());
		for(int i=1;i<=11;i++){
			Trainee trainee=new Trainee(i,"trainee "+i);
			traineeService.add(trainee);
			Trainer trainer =new Trainer(i,"trainer "+i);
			trainerService.add(trainer);
		}
		groupService.group();
	}
}
