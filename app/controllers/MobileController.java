package controllers;

import java.io.File;
import java.util.List;

import jsonvo.MobileResponse;
import jsonvo.mobileVo.ReciverVo;
import models.IClassMsg;
import models.Member;
import models.PostMsgIdx;
import models.RichPost;
import models.RichPostReply;

import org.apache.commons.io.FileUtils;

import play.db.jpa.GenericModel;
import utils.gson.ReplyGsonSerializer;
import cn.bran.play.JapidController;

public class MobileController extends JapidController {

	public static void login(String username, String pwd, Integer keep,
			String forwordUrl) {

		Member first = Member.find("username=? and pwd=?", username, pwd)
				.first();
		MobileResponse reponse = null;
		if (first != null) {
			reponse = MobileResponse.createSucc();
			reponse.result.put("loginMember", first);
		} else {
			reponse = MobileResponse.createFail("用户名或密码错误");
		}
		System.out.println("login:----username:" + username + "pwd:" + pwd
				+ ",response:" + reponse.reponseHead.success);
		renderJSON(reponse);
	}

	public static void newmsgidx(Long uid, String pwd) {
		List<GenericModel> findAll = PostMsgIdx.findAll();
		MobileResponse reponse = MobileResponse.createSucc();
		reponse.result.put("typeList", findAll);
		renderJSON(reponse);
	}

	public static void message() {
		List<GenericModel> findAll = IClassMsg.findAll();
		MobileResponse reponse = MobileResponse.createSucc();
		reponse.result.put("typeList", findAll);
		renderJSON(reponse);
	}

	public static void uploadSound(File file, String otherArg) {
		if (file != null) {
			System.out.println("flie name:" + file.getName() + ", file size:"
					+ file.length());
			System.out.println("orther argument is:" + otherArg);
			try {
				File localFile = new File("test_sample.caf");
				FileUtils.copyFile(file, localFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("can not get file argument.");
		}

	}

	public static void richpostlist(String lastUpdateTime, Long uid, Long psize) {
		System.out.println("richpostlist be invoke");
		System.out.println("lastUpdateTime=" + lastUpdateTime + ", uid=" + uid
				+ ", psize=" + psize);
		List<RichPost> plist = RichPost.findAll();
		MobileResponse response = MobileResponse.createSucc();
		response.result.put("postList", plist);
		renderJSON(response, new ReplyGsonSerializer());
	}

	public static void linkman(Long uid) {
		System.out.println("linkman be invoke, uid=" + uid);
		List<ReciverVo> result = ReciverVo.createTestData();
		MobileResponse response = MobileResponse.createSucc();
		response.result.put("groupList", result);
		renderJSON(response);
	}

	public static void richpostreplys() {
		RichPost r = RichPost.find("poster='客服cic'").first();
		List<RichPostReply> replys = RichPostReply.find("topic=?", r).fetch();

		MobileResponse response = MobileResponse.createSucc();
		response.result.put("replys", replys);
		renderJSON(response, new ReplyGsonSerializer());
	}

}
