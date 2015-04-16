package controllers;

import play.*;
import play.api.mvc.Session;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*; 
import views.html.*;
import models.*;

public class Application extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result main() {
        int num_team=Team.find.all().size();

        return ok(main.render(num_team,Team.find.all()) );
    }
    public static Result login(){
    	return ok(login.render(Form.form(Login.class)));
    	
    }
    @Security.Authenticated(Secured.class)
    public static Result team(Long id){
        Team temp_team = Team.findTeamID(id);

        return ok(team.render(temp_team,Rate_Criteria.find.all()));
    }
    public static Result authenticate(){
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	if(loginForm.hasErrors()){
    		return badRequest(login.render(loginForm));
    		
    		
    	}else{
    		
    		session().clear();
    		session("username",loginForm.get().username);
            System.out.println(session().get("username"));
    		return redirect(routes.Application.main());	
    			
    	}
        
    	
    }
    @Security.Authenticated(Secured.class)
    public static Result result(){
        return ok(result.render(Team.getRate(), Team.getRank(), Team.find.all(), Rate_Criteria.find.all()));
    }
    @Security.Authenticated(Secured.class)
    public static Result vote(){

        return ok(vote.render(Rate_Criteria.find.all()));
    }
    @Security.Authenticated(Secured.class)
    public static Result voteTeam(Long id){
        Rate_Criteria temp_topic = Rate_Criteria.findTopicID(id);
        return ok(voteTeam.render(Team.find.all(),temp_topic));
    }
    @Security.Authenticated(Secured.class)
    public static Result addTeam(){
        

        return ok(newteam.render());
    }

    public static Result addAccount(){
        System.out.println();
        return ok(newaccount.render());
    }
    @Security.Authenticated(Secured.class)
    public static Result addTopic(){

        return ok(newtopic.render());
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

}
