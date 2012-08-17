package jsonvo.mobileVo;

import java.util.ArrayList;
import java.util.List;

import models.Member;

public class MemberVo extends BaseMobileVo {
	public String dtype;
	public String fullName;
	public String username;
	public String nameTitle;
	public String avatar;
	public String email;
	public String phoneNum;
	public boolean isRemind;
	public boolean hasRelativeAccount;
	public String relativeAccount;
	public Long createTime;

	public static MemberVo memberVoFromMember(Member member) {
		MemberVo vo = new MemberVo();
		vo.id = member.id;
		vo.dtype = member.getClass().getSimpleName();
		vo.username = member.username;
		vo.fullName = member.fullName;
		vo.nameTitle = member.getNameTileOfMember();
		vo.avatar = member.avatar;
		vo.email = member.email;
		vo.phoneNum = member.phoneNum;
		vo.isRemind = member.isRemind;
		vo.hasRelativeAccount = member.hasRelativeAccount;
		vo.relativeAccount = member.relativeAccount;
		vo.createTime = member.createTime.getTime();
		return vo;
	}

	public static List<MemberVo> getMemberVosFromMemberList(
			List<Member> memberList) {
		List<MemberVo> memberVos = new ArrayList<MemberVo>();
		for (Member member : memberList) {
			memberVos.add(memberVoFromMember(member));
		}
		return memberVos;
	}

}
