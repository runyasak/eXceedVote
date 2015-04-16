package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.*;
import play.mvc.*;
import views.html.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
    public static Map getRate(){
        int teamSize = Team.find.all().size();
        int topicSize = Topic.find.all().size();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for( int i = 1; i <= teamSize; i++){
            String teamName = Team.find.where().eq("ID", i).findUnique().team_name;
            int score = sumScore(1,1);
            map.put(teamName, score);
        }
        // String[] result = new String[teamSize];
        // for(int i = 0; i < teamSize; i++){
        //     result[i] = Team.find.select("team_name").where().eq("ID", i)+"";
        //     result[i] += ","+Rate_Categories.find.select("Rate_Categories.score").where()
        //                         .eq("Rate.teams_id", i)
        //                         .eq("Rate.rate_cate", "Rate_Categories.id")
        //                         .eq("Rate_Categories.topic_id", 0);
        // }
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

}
