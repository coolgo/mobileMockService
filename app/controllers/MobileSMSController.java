package controllers;

import java.util.Date;
import java.util.List;

import jsonvo.MobileResponse;
import models.SMS;
import cn.bran.play.JapidController;

public class MobileSMSController extends JapidController {

	public static void getSMSDetail(String name, Long gid, Long mid, Long pno,
			Long psize) {
		System.out.println("i am coming," + new Date() + " and testname:"
				+ name + ",gid:" + gid + ",mid:" + mid);
		MobileResponse mobileResponse = MobileResponse.createSucc();
		// List<SMSVo> smsList = SMSVo.getSMSVoList();
		List<SMS> smsList = SMS.findAll();
		mobileResponse.result.put("smsList", smsList);
		mobileResponse.result.put("listCount", smsList.size());
		renderJSON(mobileResponse);
	}

	public static void testSMS() {
		List<SMS> smsList = SMS.findAll();
		MobileResponse mobileResponse = MobileResponse.createSucc();
		mobileResponse.result.put("smsList", smsList);
		mobileResponse.result.put("listCount", smsList.size());
		renderJSON(mobileResponse);
	}

	public static void getSMSList(Long mid, Long lasUpdateTime, Long psize) {

	}
}
