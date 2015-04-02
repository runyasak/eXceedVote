package controllers;

import models.*;
import models.Team;
import models.Topic;
import models.Vote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.testresult2;

import java.util.List;
import java.util.Map;


public class RateController extends Controller{
    public static Result main(){
        return redirect(routes.Application.main());
    }
    public static Result saveRate(Long teams_id,int score ,Long topic_id){
        Rate newRate =new Rate();

        Topic newTopic=Topic.findTopicID(topic_id);
        Account newAccount=Account.findAccount(session().get("username"));

        Team newTeam = Team.findTeamID(teams_id);

        Rate_Categories rate_cate =new Rate_Categories();
        rate_cate.rates.add(newRate);

        rate_cate.score=score;
        rate_cate.topics=newTopic;

       newRate.teams=newTeam;
        newRate.users=newAccount;
        newRate.rate_cate=rate_cate;

        newTeam.rates.add(newRate);
        newAccount.rates.add(newRate);
       newTopic.rate_cate.add(rate_cate);



        rate_cate.save();
        newRate.save();

        return ok();
    }
    public static Result receiveRate(){
        if (request().method().equals("POST")) {
            //System.out.println("good");
            Map<String, String[]> map = request().body().asFormUrlEncoded();
            Long teams_id= Long.parseLong(map.get("teams_id")[0]);

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if(!key.equals("teams_id")) {

                   saveRate(teams_id, Integer.parseInt(value[0]), Long.parseLong(key));
                }
                System.out.println("key "+ key+" value"+ value[0] );

            }
            return ok();
        }
        else return ok();


    }

    public static List<Rate> resultRate(){
//        String sql = "SELECT vote.teams_id, COUNT(vote.teams_id)  FROM vote INNER JOIN vote_categories ON vote.vote_cate_id=vote_categories.id INNER JOIN topic ON vote_categories.topics_id = topic.id WHERE topic.topic_name = \"Beautiful\"GROUP BY vote.teams_id ";
//        RawSql rawsql= RawSqlBuilder.parse(sql).columnMapping("vote.teams_id","teams_id").create();
//
//        Query query = Ebean.find(Vote.class);
//        query.setRawSql(rawsql);
//        List list = query.findList();

        return Rate.find.all();


    }
    public static Result showResult(){

        return ok(testresult2.render(resultRate()));
    }


}
