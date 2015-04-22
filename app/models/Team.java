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

    @OneToMany(mappedBy = "teams", cascade = CascadeType.REMOVE)
    public List<Account> users;
    @OneToMany(mappedBy = "teams", cascade = CascadeType.REMOVE)
    public List<Rate> rates;
    @OneToMany(mappedBy = "teams", cascade = CascadeType.REMOVE)
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

    public static List<String> getVote(){
        int topicSize = Vote_Categories.find.all().size();
        int teamSize = Team.find.all().size();
        List<Vote> vote = new ArrayList<Vote>();
        int[][] teamCount = new int[topicSize][Team.find.all().size()];
        vote = Vote.find.all();
        for( int i = 0; i < topicSize; i++ ) {
            for( Vote v : vote ) {
                if( v.vote_rec.categories.ID == i+1 ) {
                    teamCount[i][v.teams.ID.intValue()-1]++;
                }
            }
        }
        int max = 0, team = 0;
        List<String> voteResult = new ArrayList<String>();
        for( int topicIndex = 0; topicIndex < topicSize; topicIndex++ ){
            max = 0;
            team = 0;
            for( int teamIndex = 0; teamIndex < teamSize; teamIndex++ ) {
                if( max < teamCount[topicIndex][teamIndex] ) {
                    max = teamCount[topicIndex][teamIndex];
                    team = teamIndex+1;
                }
            }
            if( team == 0 ) {
                voteResult.add("Unknown");
            }
            else {
                voteResult.add(Team.find.where().eq("ID", team).findUnique().team_name);
            }
        }
        return voteResult;
    }

    public static List<Integer> getCurrentRate(int teamID, int accountID){
        int topicSize = Rate_Criteria.find.all().size();
        int teamSize = Team.find.all().size();
        List<Integer> currentRate = new ArrayList<Integer>();
        for( int i = 0; i < topicSize; i++ ) {
            List<Rate_Records> rateRec = new ArrayList<Rate_Records>();
            for( Rate r : Rate.find.all() ){
                if( r.users.ID == accountID && r.teams.ID == teamID ) {
                    rateRec.add( r.rate_rec );
                }
            }
            for( Rate_Records record : rateRec ){
                currentRate.add(record.score);
            }
        }
        return currentRate;
    }

    public static List<Integer> getCurrentVote(int teamID, int accountID){
        int topicSize = Rate_Criteria.find.all().size();
        int teamSize = Team.find.all().size();
        List<Integer> currentVote = new ArrayList<Integer>();
        for( Vote v : Vote.find.all() ){
            if( v.users.ID == accountID && v.teams.ID == teamID) {
                currentVote.add( v.vote_rec.categories.ID.intValue() );
            }
        }
        return currentVote;
    }

}
