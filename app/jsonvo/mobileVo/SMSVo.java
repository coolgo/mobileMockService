package jsonvo.mobileVo;

import java.util.ArrayList;
import java.util.List;

import models.SMS;

public class SMSVo extends BaseMobileVo {
	public String smsType; // SMSPost,SMSReply
	public String sender;
	public String senderTitle;
	public long senderId;
	public String avatar;
	public String receivers;
	public String content;
	public String sendTime;
	public long createTimestamp;

	public SMSVo() {

	}

	public static SMSVo smsVoFromSMS(SMS sms) {
		SMSVo vo = new SMSVo();
		vo.id = sms.id;
		vo.smsType = sms.smsType.toString();
		vo.sender = sms.sender;
		vo.senderTitle = sms.senderTitle;
		vo.senderId = sms.senderId;
		vo.avatar = sms.avatar;
		vo.receivers = sms.receivers;
		vo.content = sms.content;
		vo.sendTime = sms.sendTime.toString();
		vo.createTimestamp = sms.sendTime.getTime();
		return vo;
	}

	public static List<SMSVo> getSMSVosFromSMSList(List<SMS> smsList) {
		List<SMSVo> smsVoList = new ArrayList<SMSVo>();
		for (SMS sms : smsList) {
			smsVoList.add(SMSVo.smsVoFromSMS(sms));
		}
		return smsVoList;
	}
}
