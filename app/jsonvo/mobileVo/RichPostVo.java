package jsonvo.mobileVo;

import japidviews._javatags.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import models.RichPost;
import models.RichPostReply;

public class RichPostVo extends BaseMobileVo {
	public String postType;
	public String poster;
	public Long posterId;
	public String avatar;
	public String receivers;
	public String content;
	public String imgUrl;
	public String previewImgUrl;
	public Integer previewImgWidth;
	public Integer previewImgHeight;
	public Integer totalImgCount;
	public Integer replyCount;
	public String postTime;
	public Long createTime;

	public List<ReplyVo> replyList;

	public static class ImageSize {
		public int width;
		public int hight;

		public ImageSize() {
			width = 0;
			hight = 0;
		}

		public ImageSize(int w, int h) {
			width = w;
			hight = h;
		}

		public static ImageSize getSizeFromImageUrl(String imageUrl) {
			return new ImageSize(200, 50);
		}

	}

	public static RichPostVo richPostVoFromRichPost(RichPost post,
			Integer maxReplyNum) {
		RichPostVo vo = richPostVoFromRichPostWithoutReply(post);
		List<RichPostReply> richPostReplies = RichPostReply.find("topic", post)
				.fetch(maxReplyNum);
		vo.replyList = ReplyVo.getListFromRichPostReplys(richPostReplies);
		return vo;
	}

	public static RichPostVo richPostVoFromRichPostWithoutReply(RichPost post) {
		RichPostVo vo = new RichPostVo();
		vo.id = post.id;
		vo.postType = post.postType.toString();
		vo.poster = post.creater.fullName;
		vo.posterId = post.creater.id;
		vo.avatar = post.creater.avatar;
		vo.receivers = post.receivers;
		vo.content = post.content;
		vo.replyCount = RichPostReply.countReplyByPost(post).intValue();
		vo.imgUrl = post.imageUrl;
		vo.previewImgUrl = vo.imgUrl;
		if (post.imageH != null && post.imageW != null && post.imageH > 0
				&& post.imageW > 0) {
			vo.previewImgHeight = post.imageH;
			vo.previewImgWidth = post.imageW;
		} else {
			ImageSize imgeSize = ImageSize
					.getSizeFromImageUrl(vo.previewImgUrl);
			vo.previewImgHeight = imgeSize.hight;
			vo.previewImgWidth = imgeSize.width;
		}
		vo.postTime = CommonUtils.getPassTime(post.createTime);
		vo.createTime = post.createTime.getTime();
		return vo;
	}

	public static List<RichPostVo> getRichPostVoListFromRichPosts(
			List<RichPost> richPosts, Integer maxReplyNum) {
		List<RichPostVo> richPostVos = new ArrayList<RichPostVo>();
		for (RichPost post : richPosts) {
			richPostVos.add(richPostVoFromRichPost(post, maxReplyNum));
		}
		return richPostVos;
	}

}
