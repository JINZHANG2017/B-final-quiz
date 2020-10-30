package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO GTB-工程实践: - 拼写错误
public interface GroupRepostitory extends JpaRepository<GroupEntity, Integer> {
}
