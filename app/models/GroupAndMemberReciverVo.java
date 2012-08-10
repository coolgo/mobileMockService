package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class GroupAndMemberReciverVo extends Model {
	public Integer gid;
	public Integer mid;

	public Integer r;
	public Integer g;
	public Integer b;

	public String groupName;
	public String fullName;
	public String avatar;

}
