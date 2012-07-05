package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class IClassQuiz extends Model{

	public String examinee;
	public String examineeAvatar;
	public String genericGroup;
	public Boolean isNew;
	public String content;
	public String endTime;
	public String score;
	public Integer replyCount;
	
}
