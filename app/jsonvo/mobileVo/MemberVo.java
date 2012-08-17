package jsonvo.mobileVo;

import models.Member;

public class MemberVo extends BaseMobileVo {
	public String dtype;
	public String fullName;
	public String username;
	public String nameTitle;
	public String avatar;
	public String pwd;
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
		vo.pwd = member.pwd;
		vo.email = member.email;
		vo.phoneNum = member.phoneNum;
		vo.isRemind = member.isRemind;
		vo.hasRelativeAccount = member.hasRelativeAccount;
		vo.relativeAccount = member.relativeAccount;
		vo.createTime = member.createTime.getTime();
		return vo;
	}

}
