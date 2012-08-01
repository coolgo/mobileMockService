package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import play.db.jpa.Model;

@Entity
public class SMS extends Model {

	public String sender;
	public String senderTitle;
	public long senderId;
	public String avatar;
	public String receivers;
	public String content;
	public String sendTime;

	@Enumerated(EnumType.STRING)
	public SMSType smsType;

	public enum SMSType {
		SMSPost, SMSReply
	}

	public SMS() {
	}

	public SMS(String sender, String senderTitle, long senderId, String avatar,
			String receivers, String content, String sendTime, SMSType smsType) {
		super();
		this.sender = sender;
		this.senderTitle = senderTitle;
		this.senderId = senderId;
		this.avatar = avatar;
		this.receivers = receivers;
		this.content = content;
		this.sendTime = sendTime;
		this.smsType = smsType;
	}

}
