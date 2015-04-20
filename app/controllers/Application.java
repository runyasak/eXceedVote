package controllers;

import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*; 
import views.html.*;
import models.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Application extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result main() {
        int num_team=Team.find.all().size();

        return ok(main.render(num_team,Team.find.all(),Account.findAccount(session().get("username"))) );
    }
    public static Result login(){
    	return ok(login.render(Form.form(Login.class),Account.findAccount(session().get("username"))));
    	
    }
    @Security.Authenticated(Secured.class)
    public static Result team(Long id){
        Team temp_team = Team.findTeamID(id);

        int accountID = Account.findAccount(session().get("username")).ID.intValue();
        return ok(team.render(temp_team, Rate_Criteria.find.all(), Vote_Categories.find.all(), Account.findAccountTeam(id), Team.getCurrentRate(id.intValue(),accountID), Team.getCurrentVote(id.intValue(),accountID),Account.findAccount(session().get("username")) ));

    }
    public static Result authenticate(){
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	if(loginForm.hasErrors()){
    		return badRequest(login.render(loginForm,Account.findAccount(session().get("username"))));


    	}else{
    		
    		session().clear();
    		session("username",loginForm.get().username);


            Date d1 = new Date();
            LogController.saveLog(loginForm.get().username,d1);
            System.out.println(session().get("username"));
    		return redirect(routes.Application.main());	
    			
    	}
    	
    }
    @Security.Authenticated(Secured.class)
    public static Result result(){
        if(Account.findAccount(session().get("username")).type==2) {
            return ok(result.render(Team.getRate(), Team.getRank(), Team.find.all(), Rate_Criteria.find.all(), Vote_Categories.find.all(), Team.getVote()));
        }
        else{
            return redirect(
                    routes.Application.main()
            );

        }
    }

//    @Security.Authenticated(Secured.class)
//    public static Result vote(){
//
//        return ok(vote.render(Rate_Criteria.find.all()));
//    }
//    @Security.Authenticated(Secured.class)
//    public static Result voteTeam(Long id){
//        Rate_Criteria temp_topic = Rate_Criteria.findTopicID(id);
//        return ok(voteTeam.render(Team.find.all(),temp_topic));
//    }
    @Security.Authenticated(Secured.class)
    public static Result addTeam(){
        if(Account.findAccount(session().get("username")).type==2) {


            return ok(newteam.render());
        }
        else{
            return redirect(
                    routes.Application.main()
            );

        }
    }

    public static Result addAccount(){
        System.out.println();
        if(Account.findAccount(session().get("username")).type==2) {
            return ok(newaccount.render());
        }
        else{
            return redirect(
                    routes.Application.main()
            );

        }
    }
    @Security.Authenticated(Secured.class)
    public static Result addTopic(){
        if(Account.findAccount(session().get("username")).type==2) {
            return ok(newtopic.render());
        }
        else{
            return redirect(
                    routes.Application.main()
            );

        }
    }
    @Security.Authenticated(Secured.class)
    public static Result addCategory(){
        if(Account.findAccount(session().get("username")).type==2) {
            return ok(newcategory.render());
        }
        else{
            return redirect(
                    routes.Application.main()
            );

        }
    }

    @Security.Authenticated(Secured.class)
    public static Result editAccount(){
        int num_team=Team.find.all().size();


        return ok(editAccount.render(num_team, Team.find.all(), Account.findAccount(session().get("username"))));


    }
    public static Result editTeam(Long id){
        Team editTeam =Team.findTeamID(id);
        System.out.println("");
        return ok(editteam.render(editTeam,Account.findAccount(session().get("username"))));
    }
    
    public static class Login{
    	public String username;
    	public String password;
    	public String validate(){
    		if(Account.authenticate(username,password)==null){
    			return "Invalid user or password";
    		}
    		return null;    		
    	}
    }

    public static Result logout() {
        session().clear();
        flash("success", "You have been logged out");
        return redirect(
            routes.Application.login()
        );
    }
}
