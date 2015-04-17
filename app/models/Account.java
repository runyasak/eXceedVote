package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;


@Entity
public class Account extends Model {
	
    @Id
    public Long ID;
	public String username;
	public String password;
	public int type;
    @ManyToOne
    public Team teams;

    @OneToMany(mappedBy = "users")
    public List<Rate> rates;
    @OneToMany(mappedBy = "users")
    public List<Vote> votes;
    public static Finder<Long,Account> find=new Finder<Long,Account>(Long.class,Account.class);

    public void addRate(Rate rates){

        this.rates.add(rates);



    }
    public void addVote(Vote votes){
        this.votes.add(votes);


    }
	public static Account authenticate(String username,String password){

		
		
		return Account.find.where().eq("username", username).eq("password" , password).findUnique();
		
	}
    public static Account findAccount(String username){

        return Account.find.where().eq("username", username).findUnique();

    }
    public static List<Account> findAccountTeam(Long id){



        return Account.find.where().eq("teams_id",id).findList();

    }

	
}
