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
    public static Result AddTopic(String Topicname){
        Vote newVote= new Vote();


        return main();
    }
    public static Result getAddtopic(){
        Form<Topic> newTopicForm = Form.form(Topic.class).bindFromRequest();
        AddTopic(newTopicForm.get().topic_name);

        return main();

    }

}