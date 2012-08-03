package jsonvo.mobileVo;

import java.util.ArrayList;

public class ReciverVo {

	public Long gid;
	public String groupName;
	public Long r;
	public Long g;
	public Long b;
	public Boolean isOwner;

	public static class MemberVo {
		public Long mid;
		public String fullName;
		public String avatar;
	}

	public ArrayList<ReciverVo.MemberVo> memberList;

	public ReciverVo() {
		memberList = new ArrayList<ReciverVo.MemberVo>();
	}

	public static ArrayList<ReciverVo> createTestData() {
		ArrayList<ReciverVo> result = new ArrayList<ReciverVo>();
		ReciverVo reciverPointer = null;
		ReciverVo.MemberVo meberPointer = null;

		reciverPointer = new ReciverVo();
		reciverPointer.gid = 1l;
		reciverPointer.groupName = "2011级西瓜班";
		reciverPointer.r = 80l;
		reciverPointer.g = 173l;
		reciverPointer.b = 27l;

		meberPointer = new MemberVo();
		meberPointer.avatar = "http://www.iclass.com/public/img/defaultAvatar/thi_ava12.png";
		meberPointer.fullName = "大雷";
		meberPointer.mid = 1l;
		reciverPointer.memberList.add(meberPointer);
		meberPointer = new MemberVo();
		meberPointer.avatar = "http://www.iclass.com/public/img/defaultAvatar/thi_ava11.png";
		meberPointer.fullName = "点点";
		meberPointer.mid = 2l;
		reciverPointer.memberList.add(meberPointer);
		meberPointer = new MemberVo();
		meberPointer.avatar = "http://www.iclass.com/public/img/defaultAvatar/thi_ava10.png";
		meberPointer.fullName = "东东";
		meberPointer.mid = 3l;
		reciverPointer.memberList.add(meberPointer);
		meberPointer = new MemberVo();
		meberPointer.avatar = "http://www.iclass.com/public/img/defaultAvatar/thi_ava2.png";
		meberPointer.fullName = "断欲";
		meberPointer.mid = 4l;
		reciverPointer.memberList.add(meberPointer);
		result.add(reciverPointer);

		reciverPointer = new ReciverVo();
		reciverPointer.gid = 2l;
		reciverPointer.groupName = "2011级香蕉班";
		reciverPointer.r = 171l;
		reciverPointer.g = 111l;
		reciverPointer.b = 28l;
		result.add(reciverPointer);

		reciverPointer = new ReciverVo();
		reciverPointer.gid = 3l;
		reciverPointer.groupName = "2011级草莓班";
		reciverPointer.r = 175l;
		reciverPointer.g = 21l;
		reciverPointer.b = 64l;
		result.add(reciverPointer);

		return result;
	}
}
