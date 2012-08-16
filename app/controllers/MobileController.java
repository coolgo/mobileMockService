package controllers;

import java.io.File;
import java.util.Date;
import java.util.List;

import jsonvo.MobileResponse;
import jsonvo.mobileVo.MemberVo;
import jsonvo.mobileVo.ReciverVo;
import jsonvo.mobileVo.ReplyVo;
import jsonvo.mobileVo.RichPostVo;
import jsonvo.mobileVo.RichPostVo.ImageSize;
import jsonvo.mobileVo.SMSVo;
import models.Member;
import models.PostMsgIdx;
import models.RichPost;
import models.RichPost.PostType;
import models.RichPostReply;
import models.SMS;

import org.apache.commons.io.FileUtils;

import play.data.binding.As;
import play.db.jpa.GenericModel;
import utils.PictureUploadUtil;
import cn.bran.play.JapidController;

public class MobileController extends JapidController {

	private final static int PSIZE = 5;

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
				+ ",response:" + reponse.responseHead.success);
		renderJSON(reponse);
	}

	public static void personalInfo(Long uid) {
		Member member = Member.findById(uid);
		MobileResponse mobileResponse = null;
		if (null != member) {
			mobileResponse = MobileResponse.createSucc();
			mobileResponse.result.put("personal_Info",
					MemberVo.memberVoFromMember(member));
		} else {
			mobileResponse = MobileResponse.createFail("你请求的数据不存在");
		}
		renderJSON(mobileResponse);
	}

	public static void newmsgidx(Long uid, String pwd) {
		List<GenericModel> findAll = PostMsgIdx.findAll();
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

	public static void post(Long postId) {
		RichPost richPost = RichPost.findById(postId);
		MobileResponse mobileResponse = null;
		if (null != richPost) {
			mobileResponse = MobileResponse.createSucc();
			mobileResponse.result.put("post",
					RichPostVo.richPostVoFromRichPostWithoutReply(richPost));
		} else {
			mobileResponse = MobileResponse.createFail("请求的数据未找到");
		}
		renderJSON(mobileResponse);
	}

	public static void postList(Long lastUpdateTime, Long uid, Integer psize) {
		System.out.println("richpostlist be invoke");
		System.out.println("lastUpdateTime=" + lastUpdateTime + ", uid=" + uid
				+ ", psize=" + psize);
		Date lastUpdateDate = lastUpdateTime != null ? new Date(lastUpdateTime)
				: new Date();
		psize = psize != null ? psize : PSIZE;
		List<RichPost> plist = RichPost.fetchRichPostsForPaging(uid,
				lastUpdateDate, psize);
		MobileResponse response = MobileResponse.createSucc();
		response.result.put("postList",
				RichPostVo.getRichPostVoListFromRichPosts(plist, 2));
		renderJSON(response);
	}

	public static void createPost(File file, Long senderId, String content,
			PostType type, @As(value = ",") List<Long> grouprecivers,
			@As(value = ",") List<Long> memberrecivers) {
		System.out.println("fileName:" + file.getName());
		long beginTime = System.currentTimeMillis();
		// print param on console. for test
		printCreatePostParam(file, senderId, content, grouprecivers,
				memberrecivers, type.toString());
		MobileResponse response = null;
		Member creater = Member.findById(senderId);
		if (creater != null) {
			response = MobileResponse.createSucc();
			String fileUrl = null;
			ImageSize imgSize = null;
			if (file != null) {

				fileUrl = PictureUploadUtil.uploadFileWithPngImage(file);
				imgSize = PictureUploadUtil.getImageSizeFromImage(file);
			}

			RichPost.createRichPost(creater, fileUrl, imgSize, content, type,
					grouprecivers, memberrecivers);
		} else {
			response = MobileResponse.createFail("你尚未登陆");
		}

		System.out.println("use time:"
				+ (System.currentTimeMillis() - beginTime));
		renderJSON(response);
	}

	public static void linkmanList(Long uid) {
		System.out.println("linkman be invoke, uid=" + uid);
		List<ReciverVo> result = ReciverVo.createTestData();
		MobileResponse response = MobileResponse.createSucc();
		response.result.put("groupList", result);
		renderJSON(response);
	}

	@Deprecated
	public static void uploadtest(File file, String ortherArg) {
		MobileResponse response = null;
		if (file != null) {
			response = MobileResponse.createSucc();
			String info = "param, file name = " + file.getName()
					+ ", file size=" + file.length() + ", orther args="
					+ ortherArg;
			response.result.put("sysInfo", info);
			System.out.println(info);
		} else {
			response = MobileResponse.createFail("can not get file param");
			System.out.println("can not get file.");
		}
		renderJSON(response);
	}

	private static void printCreatePostParam(File f, Long sid, String c,
			List<Long> gs, List<Long> ms, String type) {
		String info = (f == null ? "nofile" : f.getName() + ":" + f.length())
				+ ", sid=" + sid + ", content=" + c + ", groupreciver=" + gs
				+ ", memberreciver=" + ms + ", type=" + type;
		System.out.println(info);
	}

	public static void replyList(Long postId, Integer pno, Integer psize) {
		pno = pno != null ? pno : 1;
		psize = psize != null ? psize : Integer.MAX_VALUE;
		MobileResponse mobileResponse = null;
		RichPost richPost = RichPost.findById(postId);
		if (null != richPost) {
			mobileResponse = MobileResponse.createSucc();
			List<RichPostReply> richPostReplies = RichPostReply.find("topic",
					richPost).fetch(pno, psize);
			mobileResponse.result.put("replyList",
					ReplyVo.getListFromRichPostReplys(richPostReplies));
		} else {
			mobileResponse = MobileResponse.createFail("请求的数据出错了");
		}
		System.out.println("postId，replyList:" + postId + "," + mobileResponse);
		renderJSON(mobileResponse);
	}

	public static void newReplyList(Long uid, Long lastUpdateTime, Integer psize) {
		psize = psize != null ? psize : PSIZE;
		MobileResponse mobileResponse = MobileResponse.createSucc();
		List<RichPost> richPosts = RichPostReply.fetchRichPostHasReply(uid,
				psize);
		mobileResponse.result.put("newReplyList",
				RichPostVo.getRichPostVoListFromRichPosts(richPosts, 1));
		renderJSON(mobileResponse);
	}

	public static void createReply(Long uid, Long postId, String content) {
		Member member = Member.findById(uid);
		RichPost post = RichPost.findById(postId);
		MobileResponse mobileResponse = null;
		if (member != null && post != null) {
			mobileResponse = MobileResponse.createSucc();
			RichPostReply.createRichPostReply(member, post, content);
		} else {
			mobileResponse = MobileResponse.createFail("你没有回复的权限");
		}
		renderJSON(mobileResponse);
	}

	public static void smsList(String uid, Long lastUpdateTime, Integer psize) {
		System.out.println("uid:" + uid + "psize:" + psize);
		Date beginDate = lastUpdateTime != null ? new Date(lastUpdateTime)
				: new Date();
		psize = psize != null ? psize : PSIZE;
		List<SMS> smsList = SMS.fetchSMSListIgnorePno(beginDate, psize);
		List<SMSVo> smsVoList = SMSVo.getSMSVosFromSMSList(smsList);
		MobileResponse mobileResponse = MobileResponse.createSucc();
		mobileResponse.result.put("smsList", smsVoList);
		mobileResponse.result.put("listCount", smsVoList.size());
		renderJSON(mobileResponse);
	}

}
