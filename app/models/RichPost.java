package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

// add something for test git commit.
@Entity
public class RichPost extends Model {
	public String postType;
	public String poster;
	public Long posterId;
	public String avatar;
	public String receivers;
	public String content;
	public String imageUrl;
	public Long imageW;
	public Long imageH;
	public Long replyCount;
	public String postTime;
	public Long postTimeNumberValue;

}
