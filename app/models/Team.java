package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;


@Entity
public class Team extends Model {

    @Id
    public Long ID;
    public String team_name;

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

}
