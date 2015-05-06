package models;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

@Entity
public class Account extends Model {
	
    @Id
    public Long ID;
	public String username;
	public String password;
	public int type;
    public String name;
    public String lastname;
    public String studentID;
    @ManyToOne
    public Team teams;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    public List<Rate> rates;
    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE)
    public List<Vote> votes;
    public static Finder<Long,Account> find=new Finder<Long,Account>(Long.class,Account.class);
    public Account(){


    }
    public Account(String username,String password,int type){
        this.username=username;
        this.password=password;
        this.type=type;

    }

    public void addRate(Rate rates){

        this.rates.add(rates);



    }
    public void addVote(Vote votes){
        this.votes.add(votes);


    }
	public static Account authenticate(String username,String password){

        Account user_account = Account.findAccount(username);
        System.out.println("hello");
        if (user_account != null && BCrypt.checkpw(password, user_account.password)) {
            return user_account;
        } else {
            return null;
        }
		
	}
    public static Account findAccount(String username){

        return Account.find.where().eq("username", username).findUnique();

    }
    public static List<Account> findAccountTeam(Long id){



        return Account.find.where().eq("teams_id",id).findList();

    }
    public static Account create(String username,String password,int type){
            Account account =new Account(username,BCrypt.hashpw(password,BCrypt.gensalt()),type);

            account.save();



            return account;
    }

	
}
