package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

@Entity
public class Vote extends Model {

    @Id
    public Long ID;
    @ManyToOne
    public Account users;
    @ManyToOne
    public Team teams;
    @ManyToOne
    public Vote_Records vote_rec;

    public static Finder<Long,Vote> find=new Finder<Long,Vote>(Long.class,Vote.class);




}
