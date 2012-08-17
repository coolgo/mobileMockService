package jsonvo.mobileVo;

import java.util.ArrayList;
import java.util.List;

import models.GenericGroup;
import models.GenericGroup_Member;
import models.Member;

public class GenericGroupVo extends BaseMobileVo {
	public Long gid;
	public String groupName;
	public Integer r;
	public Integer g;
	public Integer b;
	public Boolean isOwner;

	public List<SimpleMemberVo> memberList;

	public static GenericGroupVo groupMemberVoFromGroup(GenericGroup group) {
		GenericGroupVo groupMemberVo = new GenericGroupVo();
		groupMemberVo.gid = group.id;
		groupMemberVo.groupName = group.groupName;
		groupMemberVo.r = group.r;
		groupMemberVo.g = group.g;
		groupMemberVo.b = group.b;
		List<Member> members = GenericGroup_Member.memberListFromGroup(group);
		groupMemberVo.memberList = SimpleMemberVo
				.simpleMemberVosFromMemberList(members);
		return groupMemberVo;
	}

	public static List<GenericGroupVo> getGroupVoList() {
		List<GenericGroupVo> groupVos = new ArrayList<GenericGroupVo>();
		List<GenericGroup> genericGroups = GenericGroup.findAll();
		for (GenericGroup genericGroup : genericGroups) {
			groupVos.add(groupMemberVoFromGroup(genericGroup));
		}
		return groupVos;
	}
}
