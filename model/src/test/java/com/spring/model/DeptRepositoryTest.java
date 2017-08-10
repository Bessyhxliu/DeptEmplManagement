package com.spring.model;

import static org.junit.Assert.*;

import org.hibernate.Hibernate;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spring.Application;
import com.spring.model.Department;
import com.spring.repo.DeptRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class DeptRepositoryTest {

	@Before
	@After
	public void init() {
		deptRepo.deleteAll();
	}
	
	@Test
	public void testDeptRepoDeletAll() {
		int total = 0;
		deptRepo.deleteAll();
		Iterable<Department> depts = deptRepo.findAll();
		for(Department dept:depts){
			total++;
		}
		assertEquals(0, total);
	}
	
	@Test
	public void testDeptRepoSave() {
		String name = "test123";
		Department dept = new Department();
		dept.setDeptName(name);
		Department dept1 = deptRepo.save(dept);
		
		assertEquals(name, dept1.getDeptName());
	}

	@Test
	public void testDeptRepDeleteById(){
		String name = "test123";
		Department dept = new Department();
		dept.setDeptName(name);
		Department dept1 = deptRepo.save(dept);
		deptRepo.delete(dept1.getDeptId());
		
		int total = 0;
		deptRepo.deleteAll();
		Iterable<Department> depts = deptRepo.findAll();
		for(Department temp:depts){
			total++;
		}
		assertEquals(0, total);		
	}
	
	@Autowired
	private DeptRepository deptRepo;
}
