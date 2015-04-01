package controllers;




import com.fasterxml.jackson.databind.JsonNode;
import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

import java.util.Map;

public class testController extends Controller {

    public static Result main() {

        return ok(testjson.render("5"));
    }
    public static Result getjson(){
        if (request().method().equals("POST")) {
            System.out.println("good");
            Map<String, String[]> map = request().body().asFormUrlEncoded();
            System.out.println(map.get("t1")[0]);

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
               System.out.println("key "+ key+" value"+ value[0] );

            }
            return ok();
        }
        else return ok();


    }


}


