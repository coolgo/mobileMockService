package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jsonvo.mobileVo.RichPostVo.ImageSize;
import play.db.jpa.Model;

// add something for test git commit.
@Entity
public class RichPost extends Model {
	@Enumerated(EnumType.STRING)
	public PostType postType;

	public String receivers;
	public String content;
	public String imageUrl;
	public Integer imageW;
	public Integer imageH;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createTime;

	@ManyToOne
	public Member creater;

	public RichPost() {
	}

	public enum PostType {
		PostBadge, PostMessage, PostPhoto, PostAssignment, PostSMS
	}

	public static List<RichPost> fetchRichPostsForPaging(Long memberId,
			Date lastUpdateDate, Integer psize) {
		String hql = "select post  from RichPost post where createTime<? order by createTime desc ";
		return RichPost.find(hql, lastUpdateDate).fetch(psize);
	}

	public static void createRichPost(Member creater, String fileUrl,
			ImageSize imgSize, String content, PostType postType,
			List<Long> grouprecivers, List<Long> memberrecivers) {
		RichPost rp = new RichPost();
		rp.imageUrl = fileUrl;
		rp.imageH = imgSize.hight;
		rp.imageW = imgSize.width;
		rp.createTime = new Date();
		rp.creater = creater;
		rp.receivers = getReceiversByGroupAndMember(grouprecivers,
				memberrecivers);
		rp.postType = postType;
		rp.content = content;
		rp.createTime = new Date();
		rp.save();
	}

	private static String getReceiversByGroupAndMember(
			List<Long> grouprecivers, List<Long> memberrecivers) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < grouprecivers.size(); i++) {
			GenericGroup g = GenericGroup.findById(grouprecivers.get(i));
			sb.append(g.groupName).append(",");
		}

		for (int i = 0; i < memberrecivers.size(); i++) {
			Member m = Member.findById(memberrecivers.get(i));
			sb.append(m.fullName);
			if (i != memberrecivers.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();

	}
}
