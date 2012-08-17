package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class GenericGroup extends Model {
	public Integer r;
	public Integer g;
	public Integer b;

	public String groupName;

}
