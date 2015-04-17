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
        int account_size = Account.find.all().size();
        int team_size = Team.find.all().size();
        int criteria_size= Rate_Criteria.find.all().size();
        int category_size=Vote_Categories.find.all().size();

        return ok(adminpage.render(account_size,team_size,criteria_size,category_size));
    }

}
