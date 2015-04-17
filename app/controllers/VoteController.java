package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import models.*;
import models.Team;
import models.Rate_Criteria;
import models.Vote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.testresult;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class VoteController extends Controller{

    public static Result main(){
        return redirect(routes.Application.main());
    }

    public static Result saveVote(Long teams_id,String topic_name){
        Vote newVote =new Vote();
        Vote_Categories newTopic=Vote_Categories.findTopic(topic_name);
        Account newAccount=Account.findAccount(session().get("username"));
        Team newTeam = Team.findTeamID(teams_id);
        Vote_Records vote_rec =new Vote_Records();
        vote_rec.votes.add(newVote);
        vote_rec.categories=newTopic;
        newVote.teams=newTeam;
        newVote.users=newAccount;
        newVote.vote_rec=vote_rec;
        newTeam.votes.add(newVote);
        newAccount.votes.add(newVote);
        newTopic.vote_rec.add(vote_rec);
        
        vote_rec.save();
        newVote.save();

        return ok();
    }

    public static Result updateVote(Long teams_id,String topic_name, Long voteRecID){
        String s = "UPDATE vote set teams_id = :count where id = :id";
        SqlUpdate update = Ebean.createSqlUpdate(s);
        update.setParameter("id", voteRecID);
        update.setParameter("count", teams_id);
        Ebean.execute(update);

        return ok();
    }

    public static Result receiveVote(){
        if (request().method().equals("POST")) {
            Map<String, String[]> map = request().body().asFormUrlEncoded();
            Long teams_id= Long.parseLong(map.get("teams_id")[0]);

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if(!key.equals("teams_id")) {
                    int accountID = Account.findAccount(session().get("username")).ID.intValue();
                    List<Vote> vote = new ArrayList<Vote>();
                    vote = Vote.find.where().eq("users_id", accountID).findList();
                    for( Vote v : vote ) {
                        if( v.vote_rec.categories.ID.intValue() == Vote_Categories.findTopic( value[0] ).ID.intValue() ){
                            updateVote( teams_id, value[0], Vote_Categories.findTopic( value[0] ).ID );
                            return ok();
                        }
                    }
                    saveVote(teams_id,value[0]);
                }
                // System.out.println("key "+ key+" value"+ value[0] );
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
