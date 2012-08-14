package controllers;

import jsonvo.MobileResponse;
import jsonvo.mobileVo.DetailPostVo;
import models.RichPost;
import cn.bran.play.JapidController;

public class MobilePostController extends JapidController {

	public static void postDetail(Long postId) {
		RichPost richPost = RichPost.findById(postId);
		MobileResponse mobileResponse = null;
		if (null != richPost) {
			mobileResponse = MobileResponse.createSucc();
			mobileResponse.result.put("detailPost",
					DetailPostVo.createDetailPostVoByRichPost(richPost));
		} else {
			mobileResponse = MobileResponse.createFail("你所请求的数据有错误");
		}
		System.out.println("postDetail-mobileResponse:" + mobileResponse);
		renderJSON(mobileResponse);
	}
}
