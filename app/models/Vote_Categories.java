package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;
@Entity
public class Vote_Categories extends Model{


    @Id
    public Long ID;

    @ManyToOne
    public Topic topics;
    @OneToMany(mappedBy="vote_cate")
    public List<Vote> votes;

    public static Model.Finder<Long,Vote_Categories> find=new Model.Finder<Long,Vote_Categories>(Long.class,Vote_Categories.class);

}
