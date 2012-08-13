package controllers;

import java.util.List;

import models.Member;
import models.PostMsgIdx;
import models.RichPost;
import models.SMS;
import play.db.jpa.GenericModel;
import cn.bran.play.JapidController;

public class RecorderController extends JapidController {

	public static final String detailPage = "RecorderController/mobilemember.html";

	public static void member() {
		List<Member> findAll = Member.findAll();
		renderJapidWith(detailPage, findAll);
	}

	public static void postmsgidx() {
		List<GenericModel> findAll = PostMsgIdx.findAll();
		renderJapidWith(detailPage, findAll);
	}

	public static void richpost() {
		List<RichPost> findAll = RichPost.findAll();
		renderJapidWith(detailPage, findAll);
	}

	public static void sms() {
		List<GenericModel> findAll = SMS.findAll();
		renderJapidWith(detailPage, findAll);
	}

}
