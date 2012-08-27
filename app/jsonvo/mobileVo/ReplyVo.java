package jsonvo.mobileVo;

import japidviews._javatags.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.RichPostReply;

import org.apache.commons.lang.time.DateUtils;

public class ReplyVo extends BaseMobileVo {
	public String replyer;
	public Long replyerId;
	public String avatar;
	public String content;
	public String replyTime;
	public Long createTime;

	public static ReplyVo replyVoFromRichPostReply(RichPostReply reply) {
		ReplyVo vo = new ReplyVo();
		vo.id = reply.id;
		vo.replyer = reply.creater.fullName;
		vo.replyerId = reply.creater.id;
		vo.avatar = reply.creater.avatar;
		vo.content = reply.content;
		vo.replyTime = CommonUtils
				.getPassTimeForMobileService(reply.createTime);
		vo.createTime = reply.createTime.getTime();
		if (DateUtils.isSameDay(new Date(), reply.createTime)) {
			vo.isNew = true;
		}
		return vo;
	}

	public static List<ReplyVo> getListFromRichPostReplys(
			List<RichPostReply> richPostReplies) {
		List<ReplyVo> replyVos = new ArrayList<ReplyVo>();
		for (RichPostReply r : richPostReplies) {
			replyVos.add(replyVoFromRichPostReply(r));
		}
		return replyVos;
	}

}
