package controllers;

import models.Team;
import models.Topic;
import models.Vote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class VoteController extends Controller{
    public static Result main(){
        return redirect(routes.Application.main());
    }



}
