package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

public class Register extends Controller {
	public static Result index(){
		
		return redirect(routes.Application.index());
		
    	
	}
	public  static Result addAccount(){
		//Account a = new Account ("aaa@aaa","bbb",1);
    	//a.save();
		return index();
	}

 

}

