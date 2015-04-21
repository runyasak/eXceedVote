package controllers;




import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

public class ConfigController extends Controller {

    public static Result openvote() {
        for(Config config:Config.find.all()){
               config.open=true;
                config.update();
        }
        flash("success", "Close Vote");
        return redirect(
                routes.AdminController.main()
                //routes.Application.main()
        );

    }
    public static  Result closevote(){
        for(Config config:Config.find.all()){
            config.open=false;

            config.update();

        }

        return redirect(
                routes.AdminController.main()
        );


    }

}
