package controllers;

import java.io.File;
import java.util.List;

import jsonvo.MobileResponse;
import models.IClassMsg;
import models.Member;
import models.PostMsgIdx;

import org.apache.commons.io.FileUtils;

import play.db.jpa.GenericModel;
import cn.bran.play.JapidController;

public class MobileController extends JapidController {

	public static void login(String uname, String pwd, Boolean keep,
			String forwordUrl) {
		Member first = Member.find("fullName=?", uname).first();
		MobileResponse reponse = null;
		if (first != null) {
			reponse = MobileResponse.createSucc();
			reponse.result.put("currentUser", first);
		} else {
			reponse = MobileResponse.createFail("用户名或密码错误");
		}
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

}
