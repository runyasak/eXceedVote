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
    public String project_name;
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
        int topicSize = Rate_Criteria.find.all().size();
        MultiKeyMap map = new MultiKeyMap();

        for( int i=1; i <= teamSize; i++){
            for( int j = 1; j <= topicSize; j++){
                String teamName = Team.find.where().eq("ID", i).findUnique().team_name;
                String topicName = Rate_Criteria.find.where().eq("ID", j).findUnique().criteria_name;
                int score = sumScore(i,j);
                map.put(teamName, topicName, score);
            }
        }
        return map;
    }

    public static int sumScore(int teamId, int topicId){
        List<Rate> rate = new ArrayList<Rate>();
        rate = Rate.find.all();
        List<Rate_Records> rateCate = new ArrayList<Rate_Records>();
        rateCate = Rate_Records.find.all();
        int score = 0;
        for( Rate r : rate ){
            rateCate = Rate_Records.find.where().eq("id", r.rate_rec.ID).findList();
            for( Rate_Records rc : rateCate ) {
                if(r.teams.ID == teamId && rc.criteria.ID == topicId){
                    score += rc.score;
                }
            } 
        }
        return score;
    }

    public static MultiKeyMap getRank(){
        int teamSize = Team.find.all().size();
        int topicSize = Rate_Criteria.find.all().size();
        MultiKeyMap map = new MultiKeyMap();
        for( int i = 1; i <= teamSize; i++){
            for( int j = 1; j <= topicSize; j++){
                String teamName = Team.find.where().eq("ID", i).findUnique().team_name;
                String topicName = Rate_Criteria.find.where().eq("ID", j).findUnique().criteria_name;
                int rank = calcRank(i,j);
                map.put(teamName, topicName, rank);
            }
        }
        return map;
    }

    public static int calcRank(int teamId, int topicId){
        List<Rate> rate = new ArrayList<Rate>();
        rate = Rate.find.all();
        List<Rate_Records> rateCate = new ArrayList<Rate_Records>();
        rateCate = Rate_Records.find.all();
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
