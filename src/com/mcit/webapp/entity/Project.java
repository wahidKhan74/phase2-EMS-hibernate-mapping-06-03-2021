package com.mcit.webapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ems_project")
public class Project {

	// properties
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "project_id")
	private int id;

	@Column(name = "project_no")
	private String projectNo;

	@Column(name = "project_title")
	private String projectTitle;

	@ManyToOne
	private Employee employee;
	
	
	//constructor
	public Project(String projectNo, String projectTitle) {
		this.projectNo = projectNo;
		this.projectTitle = projectTitle;
	}

	public Project() {
		super();
	}

	// getter & setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	// tostring
	@Override
	public String toString() {
		return "Project [id=" + id + ", projectNo=" + projectNo + ", projectTitle=" + projectTitle + "]";
	}	
	
	
}
