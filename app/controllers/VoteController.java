package controllers;

import models.*;
import models.Team;
import models.Topic;
import models.Vote;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

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


}
