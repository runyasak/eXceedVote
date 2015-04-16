package controllers;

import models.Rate_Criteria;
import models.Team;
import models.Rate_Criteria;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class CriteriaController extends Controller{

    public static Result main(){
        return redirect(routes.Application.main());
    }
    public static Result AddCriteria(String Topicname){
        Rate_Criteria criteria =new Rate_Criteria();
        criteria.criteria_name=Topicname;
        criteria.save();

        return main();
    }
    public static Result getAddCriteria(){
        Form<Rate_Criteria> newTopicForm = Form.form(Rate_Criteria.class).bindFromRequest();
        AddCriteria(newTopicForm.get().criteria_name);
        System.out.println(newTopicForm.get().criteria_name);

        return main();

    }

}
