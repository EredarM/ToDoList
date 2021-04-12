package com.example.demo.model.dao;

import com.example.demo.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDAO extends CrudRepository<Task, Integer> {
}
