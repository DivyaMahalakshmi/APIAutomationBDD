
package com.qa.rest.api;

/**
 * @author Divya Mahalakshmi S
 *
 */

public class EmployeeDataObject {

	private int id;
	private String name;
	private int age;
	private int salary;

	// Below methods aim to get and set the employee details

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAge(int empage) {
		this.age = empage;
	}

	public void setSalary(int empsalary) {
		this.salary = empsalary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getSalary() {
		return salary;
	}

}
