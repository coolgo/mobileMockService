package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.db.jpa.Model;

@Entity
public abstract class Member extends Model {

	public Member() {
	}

	public String fullName;
	public String username;
	public String avatar;
	public String pwd;
	public String email;
	public String phoneNum;
	public boolean isRemind;
	public boolean hasRelativeAccount;
	public String relativeAccount;
	@Temporal(TemporalType.TIMESTAMP)
	public Date createTime;

	public abstract String getNameTileOfMember();

}
