package models;

import javax.persistence.Entity;

@Entity
public class Teacher extends Member {

	public String subjectArea;
	public String schoolName;

	public Teacher() {
	}
}
