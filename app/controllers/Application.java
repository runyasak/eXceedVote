package controllers;




import play.*;  
import play.mvc.*;
import play.data.*;
import static play.data.Form.*; 
import views.html.*;
import models.*;

public class Application extends Controller {

    public static Result main() {
    	
    	
    		
    	
        return ok(main.render("5") );
    }
    public static Result login(){
    	return ok(login.render(Form.form(Login.class)));
    	
    }
    public static Result team(){
        return ok(team.render("5"));
    }
    public static Result authenticate(){
    	Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
    	if(loginForm.hasErrors()){
    		return badRequest(login.render(loginForm));
    		
    		
    	}else{
    		
    		session().clear();
    		session("username",loginForm.get().username);
    		return redirect(routes.Application.main());	
    			
    	}
    public static Result result(){
        return ok(result.render("5"));
    }
    	
    }
    public static Result vote(){
        return ok(vote.render());
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
