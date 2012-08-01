package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class RichPostReply extends Model {

	public String replyer;
	public String replyerId;
	public String avatar;
	public String content;
	public String replyTime;
	public Long replyTimeNumberValue;

	@ManyToOne
	public RichPost topic;
}
