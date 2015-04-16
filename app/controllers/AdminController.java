package controllers;




import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

public class AdminController extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result main() {


        return ok(adminpage.render());
    }

}
