package com.spring.repo;

import org.springframework.data.repository.CrudRepository;

import com.spring.model.Department;

public interface DeptRepository extends CrudRepository<Department, Long> {
}
