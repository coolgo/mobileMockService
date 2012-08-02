package controllers;

import java.util.List;

import models.IClassMsg;
import models.IClassQuiz;
import models.Member;
import models.PostMsgIdx;
import models.RichPost;
import models.SMS;
import play.db.jpa.GenericModel;
import cn.bran.play.JapidController;

public class RecorderController extends JapidController {

	public static final String detailPage = "RecorderController/mobilemember.html";

	public static void mobilemember() {
		List<Member> findAll = Member.findAll();
		renderJapid(findAll);
	}

	public static void postmsgidx() {
		List<GenericModel> findAll = PostMsgIdx.findAll();
		renderJapidWith(detailPage, findAll);
	}

	public static void message() {
		List<GenericModel> findAll = IClassMsg.findAll();
		renderJapidWith(detailPage, findAll);
	}

	public static void quiz() {
		List<GenericModel> findAll = IClassQuiz.findAll();
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
