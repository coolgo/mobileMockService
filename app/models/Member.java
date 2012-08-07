package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Member extends Model {

	public Member() {
	}

	public String fullName;
	public String username;
	public String avatar;
	public String dtype;
	public String pwd;

}
