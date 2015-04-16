package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;
@Entity
public class Vote_Records extends Model{


    @Id
    public Long ID;

    @ManyToOne
    public Vote_Categories categories;
    @OneToMany(mappedBy="vote_rec")
    public List<Vote> votes;

    public static Model.Finder<Long,Vote_Records> find=new Model.Finder<Long,Vote_Records>(Long.class,Vote_Records.class);

}
