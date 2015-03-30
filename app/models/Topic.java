package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;

@Entity
public class Topic extends Model {

    @Id
    public Long ID;
    public String topic_name;
    @OneToMany(mappedBy = "topics")
    public List<Rate_Categories> rate_cate;
    @OneToMany(mappedBy = "topics")
    public List<Vote_Categories> vote_cate;

    public static Finder<Long,Topic> find=new Finder<Long,Topic>(Long.class,Topic.class);



}
