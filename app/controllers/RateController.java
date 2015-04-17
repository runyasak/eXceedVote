package controllers;

import models.*;
import models.Team;
import models.Rate_Criteria;
import models.Vote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.testresult2;
import java.util.List;
import java.util.Map;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.Ebean;
import java.util.ArrayList;

public class RateController extends Controller{

    public static Result main(){
        return redirect(routes.Application.main());
    }

    public static Result saveRate(Long teams_id,int score ,Long topic_id){
        Rate newRate =new Rate();
        Rate_Criteria newTopic=Rate_Criteria.findTopicID(topic_id);
        Account newAccount=Account.findAccount(session().get("username"));
        Team newTeam = Team.findTeamID(teams_id);
        Rate_Records rate_rec =new Rate_Records();
        rate_rec.rates.add(newRate);
        rate_rec.score=score;
        rate_rec.criteria=newTopic;
        newRate.teams=newTeam;
        newRate.users=newAccount;
        newRate.rate_rec=rate_rec;
        newTeam.rates.add(newRate);
        newAccount.rates.add(newRate);
        newTopic.rate_rec.add(rate_rec);
        
        rate_rec.save();
        newRate.save();

        return ok();
    }

    public static Result updateRate(Long rateRecID, int score){
        String s = "UPDATE rate_records set score = :count where id = :id";
        SqlUpdate update = Ebean.createSqlUpdate(s);
        update.setParameter("id", rateRecID);
        update.setParameter("count", score);
        Ebean.execute(update);

        return ok();
    }

    public static Result receiveRate(){
        if (request().method().equals("POST")) {
            Map<String, String[]> map = request().body().asFormUrlEncoded();
            Long teams_id= Long.parseLong(map.get("teams_id")[0]);

            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                if(!key.equals("teams_id")) {
                    int accountID = Account.findAccount(session().get("username")).ID.intValue();
                    List<Rate> rate = new ArrayList<Rate>();
                    rate = Rate.find.where().eq("users_id", accountID).eq("teams_id", teams_id).findList();
                    for( Rate r : rate ) {
                        if( r.rate_rec.criteria.ID.intValue() == Integer.parseInt(key) ){
                            updateRate( r.rate_rec.ID, Integer.parseInt(value[0]) );
                            return ok();
                        }
                    }
                    saveRate(teams_id, Integer.parseInt(value[0]), Long.parseLong(key));
                }

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

    @Security.Authenticated(Secured.class)
    public static Result showResult(){

        return ok(testresult2.render(resultRate()));
    }



}
