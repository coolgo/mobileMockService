package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class GenericGroup_Member extends Model {
	@ManyToOne
	public GenericGroup genericGroup;
	@ManyToOne
	public Member groupMember;

	public GenericGroup_Member() {
	}

	public static List<GenericGroup_Member> fetchGenericGroup_Members() {
		return GenericGroup_Member.findAll();
	}

	public static List<Member> memberListFromGroup(GenericGroup genericGroup) {
		return GenericGroup_Member
				.find("select ggm.groupMember  from GenericGroup_Member ggm where ggm.genericGroup=? ",
						genericGroup).fetch();
	}
}
