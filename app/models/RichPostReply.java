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

	public String replyer;
	public Long replyerId;
	public String avatar;
	public String content;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createTime;

	@ManyToOne
	public RichPost topic;

	public RichPostReply() {
	}

	public static List<RichPost> fetchRichPostHasReply(Long uid, Integer psize) {
		String hql = "select post from RichPostReply r, RichPost post where post=r.topic group by post order by post.createTime desc ";
		return RichPost.find(hql).fetch(psize);
	}
}
