package jsonvo;

import java.util.HashMap;

public class MobileResponse {
    public HashMap<String, Object> result = new HashMap<String, Object>();
    public MobileResponseHead responseHead = new MobileResponseHead();
    
    public static MobileResponse createSucc(){
    	MobileResponse r = new MobileResponse();
    	r.responseHead.success = true;
    	return r;
    }
    
    public static MobileResponse createFail(String errInfo){
    	MobileResponse r = new MobileResponse();
    	r.responseHead.success = false;
    	r.responseHead.msgInfo = errInfo;
    	return r;
    }
    
}
