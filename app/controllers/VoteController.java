package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import models.*;
import models.Team;
import models.Topic;
import models.Vote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.testresult;

import java.util.List;
import java.util.Map;


public class VoteController extends Controller{
    public static Result main(){
        return redirect(routes.Application.main());
    }
    public static Result saveVote(Long teams_id,Long topic_id){
        Vote newVote =new Vote();

        Topic newTopic=Topic.findTopicID(topic_id);
        Account newAccount=Account.findAccount(session().get("username"));

        Team newTeam = Team.findTeamID(teams_id);

        Vote_Categories vote_cate =new Vote_Categories();
        vote_cate.votes.add(newVote);


        vote_cate.topics=newTopic;

        newVote.teams=newTeam;
        newVote.users=newAccount;
        newVote.vote_cate=vote_cate;

        newTeam.votes.add(newVote);
        newAccount.votes.add(newVote);
        newTopic.vote_cate.add(vote_cate);



        vote_cate.save();
        newVote.save();

        return ok();
    }
    public static Result receiveVote(){
        if (request().method().equals("POST")) {
            //System.out.println("good");
            Map<String, String[]> map = request().body().asFormUrlEncoded();
            Long teams_id= Long.parseLong(map.get("teams_id")[0]);

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if(!key.equals("teams_id")) {

                    saveVote(teams_id, Long.parseLong(value[0]));
                }
                System.out.println("key "+ key+" value"+ value[0] );

            }
            return ok();
        }
        else return ok();


    }
    public static List<Vote> resultVote(){
//        String sql = "SELECT vote.teams_id, COUNT(vote.teams_id)  FROM vote INNER JOIN vote_categories ON vote.vote_cate_id=vote_categories.id INNER JOIN topic ON vote_categories.topics_id = topic.id WHERE topic.topic_name = \"Beautiful\"GROUP BY vote.teams_id ";
//        RawSql rawsql= RawSqlBuilder.parse(sql).columnMapping("vote.teams_id","teams_id").create();
//
//        Query query = Ebean.find(Vote.class);
//        query.setRawSql(rawsql);
//        List list = query.findList();

        return Vote.find.all();


    }
    @Security.Authenticated(Secured.class)
    public static Result showResult(){

        return ok(testresult.render(resultVote()));
    }


}
