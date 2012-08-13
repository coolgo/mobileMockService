package jsonvo.mobileVo;

import java.util.List;

import models.ImageForPost;
import models.RichPost;
import models.RichPostReply;

public class DetailPostVo extends BaseMobileVo {
	public String postType;
	public String poster;
	public Long posterId;
	public String avatar;
	public String receivers;
	public String content;
	public Integer replyCount;
	public String postTime;

	public List<ImageForPost> imageList;
	public List<RichPostReply> replyList;

	public static DetailPostVo createDetailPostVoByRichPost(RichPost post) {
		DetailPostVo vo = new DetailPostVo();
		vo.id = post.id;
		vo.postType = post.postType;
		vo.poster = post.poster;
		vo.posterId = post.posterId;
		vo.avatar = post.avatar;
		vo.receivers = post.receivers;
		vo.content = post.content;
		vo.replyCount = post.replyCount.intValue();
		vo.postTime = post.postTime;

		vo.imageList = ImageForPost.find("richPost=?", post).fetch();
		vo.replyList = RichPostReply.find("topic", post).fetch();
		return vo;
	}
}
