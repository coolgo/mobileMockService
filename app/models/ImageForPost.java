package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class ImageForPost extends Model {
	@ManyToOne
	public RichPost richPost;
	public String priviewUrl;
	public int priviewWidth;
	public int priviewHigth;
	public String originalUrl;

	public ImageForPost() {
	}

}
