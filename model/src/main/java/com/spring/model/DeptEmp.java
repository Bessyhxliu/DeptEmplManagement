package com.spring.model;

import java.util.HashSet;
import java.util.Set;

public class DeptEmp {
	private Department dept;
	private Set<Employee> emps = new HashSet<Employee>();

	public DeptEmp(){}
	
	public DeptEmp(Department dept, Set<Employee> emps) {
		super();
		this.dept = dept;
		this.emps = emps;
		this.dept.setEmps(emps);
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
		this.dept.setEmps(this.emps);
	}
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
		this.dept.setEmps(this.emps);
	}
}
