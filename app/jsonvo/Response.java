package jsonvo;

import java.util.HashMap;

public class Response {
    public HashMap<String, Object> result = new HashMap<String, Object>();
    public ReponseHead reponseHead = new ReponseHead();
    
    public static Response createSucc(){
    	Response r = new Response();
    	r.reponseHead.success = true;
    	return r;
    }
    
    public static Response createFail(String errInfo){
    	Response r = new Response();
    	r.reponseHead.success = false;
    	r.reponseHead.msgInfo = errInfo;
    	return r;
    }
    
}
