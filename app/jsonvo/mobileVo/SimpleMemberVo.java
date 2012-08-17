package jsonvo.mobileVo;

import java.util.ArrayList;
import java.util.List;

import models.Member;

public class SimpleMemberVo extends BaseMobileVo {
	public Long mid;
	public String fullName;
	public String avatar;

	public static SimpleMemberVo simpleMemberVoFromMember(Member member) {
		SimpleMemberVo vo = new SimpleMemberVo();
		vo.mid = member.id;
		vo.fullName = member.fullName;
		vo.avatar = member.avatar;
		return vo;
	}

	public static List<SimpleMemberVo> simpleMemberVosFromMemberList(
			List<Member> members) {
		List<SimpleMemberVo> list = new ArrayList<SimpleMemberVo>();
		for (Member member : members) {
			list.add(simpleMemberVoFromMember(member));
		}
		return list;
	}
}
