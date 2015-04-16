package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.*;
import play.mvc.*;
import views.html.*;

import java.util.List;
import java.util.ArrayList;
import org.apache.commons.collections.map.MultiKeyMap;
import java.util.HashMap;


@Entity
public class Team extends Model {

    @Id
    public Long ID;
    public String team_name;
    public String description;

    @OneToMany(mappedBy = "teams")
    public List<Account> users;
    @OneToMany(mappedBy = "teams")
    public List<Rate> rates;
    @OneToMany(mappedBy = "teams")
    public List<Vote> votes;

    public static Finder<Long,Team> find = new Finder<Long,Team>(Long.class,Team.class);

    public static Team findTeam(String team){

        return Team.find.where().eq("team_name", team).findUnique();

    }
    public static Team findTeamID(Long id){

        return Team.find.where().eq("ID", id).findUnique();

    }
    public static MultiKeyMap getRate(){
        int teamSize = Team.find.all().size();
        int topicSize = Topic.find.all().size();
        MultiKeyMap map = new MultiKeyMap();
        for( int i = 1; i <= teamSize; i++){
            for( int j = 1; j <= topicSize; j++){
                String teamName = Team.find.where().eq("ID", i).findUnique().team_name;
                String topicName = Topic.find.where().eq("ID", j).findUnique().topic_name;
                if( teamName!=null && topicName != null ){
                    int score = sumScore(i,j);
                    map.put(teamName, topicName, score);
                }
            }
        }
        return map;
    }

    public static int sumScore(int teamId, int topicId){
        List<Rate> rate = new ArrayList<Rate>();
        rate = Rate.find.all();
        List<Rate_Categories> rateCate = new ArrayList<Rate_Categories>();
        rateCate = Rate_Categories.find.all();
        int score = 0;
        for( Rate r : rate ){
            rateCate = Rate_Categories.find.where().eq("id", r.rate_cate.ID).findList();
            for( Rate_Categories rc : rateCate ) {
                if(r.teams.ID == teamId && rc.topics.ID == topicId){
                    score += rc.score;
                }
            } 
        }
        return score;
    }

    public static MultiKeyMap getRank(){
        int teamSize = Team.find.all().size();
        int topicSize = Topic.find.all().size();
        MultiKeyMap map = new MultiKeyMap();
        for( int i = 1; i <= teamSize; i++){
            for( int j = 1; j <= topicSize; j++){
                String teamName = Team.find.where().eq("ID", i).findUnique().team_name;
                String topicName = Topic.find.where().eq("ID", j).findUnique().topic_name;
                int rank = calcRank(i,j);
                map.put(teamName, topicName, rank);
            }
        }
        return map;
    }

    public static int calcRank(int teamId, int topicId){
        List<Rate> rate = new ArrayList<Rate>();
        rate = Rate.find.all();
        List<Rate_Categories> rateCate = new ArrayList<Rate_Categories>();
        rateCate = Rate_Categories.find.all();
        int rank = 1, score = sumScore(teamId, topicId);
        for( Team t : Team.find.all() ){
            int tempId = t.ID.intValue();
            if( sumScore( tempId, topicId ) > score ){
                rank++;
            }
        }
        return rank;
    }

}
