package com.example.demo.repository;

import com.example.demo.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepostitory extends JpaRepository<GroupEntity,Integer> {
}
