package jsonvo.mobileVo;

import java.util.ArrayList;
import java.util.List;

public class SMSVo extends BaseMobileVo {
	public String smsType; // SMSPost,SMSReply
	public String sender;
	public String senderTitle;
	public long senderId;
	public String avatar;
	public String receivers;
	public String content;
	public String sendTime;

	public SMSVo() {

	}

	public SMSVo(String smsType, String sender, String senderTitle,
			long senderId, String avatar, String receivers, String content,
			String sendTime) {
		super();
		this.smsType = smsType;
		this.sender = sender;
		this.senderTitle = senderTitle;
		this.senderId = senderId;
		this.avatar = avatar;
		this.receivers = receivers;
		this.content = content;
		this.sendTime = sendTime;
	}

	public static void initData() {

	}

	public static List<SMSVo> getSMSVoList() {
		initData();
		List<SMSVo> smsVos = new ArrayList<SMSVo>();
		SMSVo vo = null;
		// 001
		vo = new SMSVo("SMSPost", "macro", "(老师)", 101,
				"http://www.iclass.com/public/img/defaultAvatar/sec_ava7.png",
				"爱班测试组", "大家都收到短信了吗", "12:50");
		vo.id = 001;
		vo.isNew = true;
		smsVos.add(vo);
		// 002
		vo = new SMSVo("SMSReply", "coolgo1", "(哥哥)", 102,
				"http://www.iclass.com/public/img/defaultAvatar/sec_ava6.png",
				"", "貌似收到了，亲", "13:05");
		vo.id = 002;
		vo.isNew = false;
		smsVos.add(vo);
		// 003
		vo = new SMSVo("SMSReply", "coolgo2", "(brother)", 103,
				"http://www.iclass.com/public/img/defaultAvatar/sec_ava5.png",
				"", "ok", "14:50");
		vo.id = 003;
		vo.isNew = true;
		smsVos.add(vo);
		// 004
		vo = new SMSVo("SMSReply", "coolgo3", "(brother)", 104,
				"http://www.iclass.com/public/img/defaultAvatar/sec_ava4.png",
				"", "yes ,i know", "15:50");
		vo.id = 004;
		vo.isNew = true;
		smsVos.add(vo);
		// 005
		vo = new SMSVo("SMSReply", "coolgo4", "(penpn)", 105,
				"http://www.iclass.com/public/img/defaultAvatar/sec_ava3.png",
				"", "yes ,i got it", "16:50");
		vo.id = 005;
		vo.isNew = true;
		smsVos.add(vo);

		return smsVos;
	}
}
