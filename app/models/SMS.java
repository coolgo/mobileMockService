package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang.time.DateFormatUtils;

import play.db.jpa.Model;

@Entity
public class SMS extends Model {

	public String sender;
	public String senderTitle;
	public long senderId;
	public String avatar;
	public String receivers;
	public String content;
	public Date sendTime;

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
		this.sendTime = sendTime;
		this.smsType = smsType;
	}

	public static List<SMS> fetchSMSList(Date beginTime, Integer pno,
			Integer psize) {
		String hql = "select sms from SMS sms where sendTime<=? ";
		return SMS.find(hql, beginTime).fetch(pno, psize);
	}

	public static void main(String[] args) {
		Double timeStamp = 1343975712.204834 * 1000;
		Date date = new Date(1343975712204L);
		System.out.println("p:" + System.currentTimeMillis() + ",date:" + date
				+ ",dateString:"
				+ DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss"));
	}

}
