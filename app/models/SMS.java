package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class SMS extends Model {

	public String sender;
	public String senderTitle;
	public long senderId;
	public String avatar;
	public String receivers;
	public String content;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createTime = new Date();

	@Enumerated(EnumType.STRING)
	public SMSType smsType;

	public enum SMSType {
		SMSPost, SMSReply
	}

	public SMS() {

	}

	public SMS(String sender, String senderTitle, long senderId, String avatar,
			String receivers, String content, Date sendTime, SMSType smsType) {
		super();
		this.sender = sender;
		this.senderTitle = senderTitle;
		this.senderId = senderId;
		this.avatar = avatar;
		this.receivers = receivers;
		this.content = content;
		this.createTime = sendTime;
		this.smsType = smsType;
	}

	public static List<SMS> fetchSMSList(Date beginDate, Integer pno,
			Integer psize) {
		String hql = "select sms from SMS sms where createTime<=? ";
		return SMS.find(hql, beginDate).fetch(pno, psize);
	}

	public static List<SMS> fetchSMSListIgnorePno(Date beginDate, Integer psize) {
		String hql = "select sms from SMS sms where createTime<=?";
		return SMS.find(hql, beginDate).fetch(psize);
	}

}
