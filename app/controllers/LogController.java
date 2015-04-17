package controllers;




import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

import java.util.Date;

public class LogController extends Controller {

    public static Result saveLog(String username,Date date) {
        Log log =new Log();
        log.username=username;
        log.date=date;
        log.save();


        return ok();
    }
    public static Result log_login() {

        return ok(loglogin.render(Log.find.all()));
    }
}
