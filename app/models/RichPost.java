package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

// add something for test git commit.
@Entity
public class RichPost extends Model {
	@Enumerated(EnumType.STRING)
	public PostType postType;
	public String poster;
	public Long posterId;
	public String avatar;
	public String receivers;
	public String content;
	public String imageUrl;
	public Integer imageW;
	public Integer imageH;
	public Integer replyCount;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createTime;

	public RichPost() {
	}

	public enum PostType {
		PostBadge, PostMessage, PostPhoto, PostAssignment, PostSMS
	}

	public static List<RichPost> fetchRichPostsForPaging(Long memberId,
			Date lastUpdateDate, Integer psize) {
		String hql = "select post  from RichPost post where createTime<=? order by createTime desc ";
		return RichPost.find(hql, lastUpdateDate).fetch(psize);
	}

	public static void createRichPost(Member creater, String content,
			PostType postType, List<Long> grouprecivers,
			List<Long> memberrecivers) {
		RichPost rp = new RichPost();
		rp.createTime = new Date();
		rp.avatar = creater.avatar;
		rp.posterId = creater.id;
		rp.receivers = "group:" + grouprecivers + ", member:" + memberrecivers;
		rp.postType = postType;
		rp.content = content;
		rp.poster = creater.fullName;
		rp.replyCount = 0;
		rp.createTime = new Date();
		rp.save();
	}
}
