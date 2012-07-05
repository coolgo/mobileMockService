package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class PostMsgIdx extends Model {
	
	public String groupCode = "";
	public String name = "";
	public Integer newcount = 0;
	public String detailUrl = "";	

}
