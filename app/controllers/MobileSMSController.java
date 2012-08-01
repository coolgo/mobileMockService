package controllers;

import java.util.Date;
import java.util.List;

import jsonvo.MobileResponse;
import jsonvo.mobileVo.SMSVo;
import cn.bran.play.JapidController;

public class MobileSMSController extends JapidController {

	public static void getSMSDetail(String name) {
		System.out.println("i am coming," + new Date() + " and testname:"
				+ name);
		MobileResponse mobileResponse = MobileResponse.createSucc();
		List<SMSVo> smsList = SMSVo.getSMSVoList();
		mobileResponse.result.put("smsList", smsList);
		mobileResponse.result.put("listCount", smsList.size());
		renderJSON(mobileResponse);
	}
}
