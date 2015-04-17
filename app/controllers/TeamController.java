package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.login;

/**
 * Created by R7 on 3/25/2015.
 */
public class TeamController extends Controller {

    public static Result main(){
        return redirect(routes.Application.main());
    }

    public static Result AddTeam(String Teamname,String description,String project_name){
        Team newTeam =new Team();
        newTeam.team_name=Teamname;
        newTeam.description=description;
        newTeam.project_name=project_name;
        newTeam.save();

        return main();
    }
    public static Result getAddteam(){
        Form<Team> newTeamForm = Form.form(Team.class).bindFromRequest();
        AddTeam(newTeamForm.get().team_name,newTeamForm.get().description,newTeamForm.get().project_name);
        System.out.println(newTeamForm.get().team_name);
        return main();

    }


}
