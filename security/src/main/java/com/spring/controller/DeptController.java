package com.spring.controller;  
  
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.model.Department;
import com.spring.model.DeptEmp;
import com.spring.repo.DeptRepository;
  
/** 
 * 
 * <p>Version: 1.0 
 */  

@RestController
public class DeptController {
	
	@Autowired
	private DeptRepository deptRepo;
	//-------------------Retrieve All Departments------------------------------
	@RequestMapping(value="/dept", method = RequestMethod.GET)
    public ResponseEntity<List<DeptEmp>> listAllDepts() {
		Iterable<Department> depts = deptRepo.findAll();
		List<DeptEmp> deptJsons = new ArrayList<DeptEmp>();
        if(depts==null){
            return new ResponseEntity<List<DeptEmp>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        } else {
        	for (Department dept: depts) {
        		DeptEmp deptJson = new DeptEmp(dept, dept.getEmps());
        		deptJsons.add(deptJson);
        	}
        	return new ResponseEntity<List<DeptEmp>>(deptJsons, HttpStatus.OK);
        }
    }
	//-------------------Retrieve One Departments by Dept Id-------------------  
    @RequestMapping(value = "/dept/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeptEmp> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);

    	Department dept = deptRepo.findOne(id);   	
        if (dept == null) {
            System.out.println("dept with id " + id + " not found");
            return new ResponseEntity<DeptEmp>(HttpStatus.NOT_FOUND);
        } else {
	        System.out.println(dept.getDeptName());
	        System.out.println(dept.getEmps().size());
	        DeptEmp deptInfo = new DeptEmp(dept, dept.getEmps());
	        return new ResponseEntity<DeptEmp>(deptInfo, HttpStatus.OK);
        }
    }
	//-------------------Create new Department---------------------------------   
    @RequestMapping(value = "/dept", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody DeptEmp deptJson,    UriComponentsBuilder ucBuilder) {

        Department dept = deptJson.getDept();
        System.out.println("Creating Dept " + deptJson.getDept());
        dept = deptRepo.save(dept);
        System.out.println("saved Dept " + dept);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/dept/{id}").buildAndExpand(dept.getDeptId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
} 