package controllers;

import models.Rate_Criteria;
import models.Team;
import models.Rate_Criteria;
import models.Vote_Categories;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;


public class CategoryController extends Controller{

    public static Result main(){
        return redirect(routes.Application.main());
    }
    public static Result AddCategory(String category_name){
        Vote_Categories category =new Vote_Categories();
        category.categories_name=category_name;
        category.save();

        return main();
    }
    public static Result getAddCriteria(){
        Form<Vote_Categories> newCategoryForm = Form.form(Vote_Categories.class).bindFromRequest();
        AddCategory(newCategoryForm.get().categories_name);
      //  System.out.println(newCategoryForm.get().categories_name);

        return main();

    }

}
