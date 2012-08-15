package jsonvo.mobileVo;

import japidviews._javatags.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import models.RichPostReply;

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
		vo.replyer = reply.replyer;
		vo.replyerId = reply.replyerId;
		vo.avatar = reply.avatar;
		vo.content = reply.content;
		vo.replyTime = CommonUtils.getPassTime(reply.createTime);
		vo.createTime = reply.createTime.getTime();
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
