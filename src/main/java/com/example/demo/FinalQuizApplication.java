package com.example.demo;

// TODO GTB-工程实践: - 未使用的import语句应该删除
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

// TODO GTB-工程实践: * 分包合理
// TODO GTB-知识点: * 对lombok的掌握不错
// TODO GTB-知识点: * 对三层架构的掌握不错
// TODO GTB-知识点: * 对Spring MVC注解的掌握不错
// TODO GTB-知识点: * 对RESTful实践的掌握相对欠缺
// TODO GTB-知识点: * 对参数校验的异常处理相对欠缺
// TODO GTB-测试: * 有Controller和Service层的测试，但只覆盖了学员相关功能
// TODO GTB-完成度: * 其它功能都完成了
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
