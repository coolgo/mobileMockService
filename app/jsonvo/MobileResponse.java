package jsonvo;

import java.util.HashMap;

public class MobileResponse {
    public HashMap<String, Object> result = new HashMap<String, Object>();
    public MobileReponseHead reponseHead = new MobileReponseHead();
    
    public static MobileResponse createSucc(){
    	MobileResponse r = new MobileResponse();
    	r.reponseHead.success = true;
    	return r;
    }
    
    public static MobileResponse createFail(String errInfo){
    	MobileResponse r = new MobileResponse();
    	r.reponseHead.success = false;
    	r.reponseHead.msgInfo = errInfo;
    	return r;
    }
    
}
