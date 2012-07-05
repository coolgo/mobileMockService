package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class IClassMsg extends Model {
	
	public String sender;
	public String content;
	public Integer replyCount = 0;
	public String avatar;
	public String sendTime;
	public Boolean isNew;

}
