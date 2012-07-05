package models;

import java.util.List;

import javax.persistence.Entity;

import play.db.jpa.Model;


@Entity
public class Member extends Model {

	
	public Member() {
	}
	
	public String fullName;
	public String avatar;
	public String dtype;
	public String pwd;

}
