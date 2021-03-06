package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public class RichPostReply extends Model {

	public String content;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createTime;

	@ManyToOne
	public RichPost topic;
	@ManyToOne
	public Member creater;

	public RichPostReply() {
	}

	public static List<RichPostReply> fetchPostRepliesByPost(RichPost post,
			Integer maxSize) {

		return RichPostReply
				.find("select r from RichPostReply r where r.topic=? order by r.createTime desc",
						post).fetch(maxSize);
	}

	public static List<RichPostReply> fetchPostReplies(Integer maxSize) {
		return RichPostReply.find(
				"select r from RichPostReply r order by r.createTime desc ")
				.fetch(maxSize);
	}

	public static Long countReplyByPost(RichPost post) {
		return RichPostReply.count("topic", post);
	}

	public static void createRichPostReply(Member creater, RichPost richPost,
			String content) {
		RichPostReply richPostReply = new RichPostReply();
		richPostReply.content = content;
		richPostReply.creater = creater;
		richPostReply.topic = richPost;
		richPostReply.createTime = new Date();
		richPostReply.save();
	}

}
