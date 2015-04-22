package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;

@Entity
public class Vote_Categories extends Model {

    @Id
    public Long ID;
    public String categories_name;

    @OneToMany(mappedBy = "categories", cascade = CascadeType.REMOVE)
    public List<Vote_Records> vote_rec;

    public static Finder<Long,Vote_Categories> find=new Finder<Long,Vote_Categories>(Long.class,Vote_Categories.class);
    public static Vote_Categories findTopic(String topic){

        return Vote_Categories.find.where().eq("categories_name", topic).findUnique();

    }
    public static Vote_Categories findTopicID(Long topic_id){

        return Vote_Categories.find.where().eq("ID", topic_id).findUnique();

    }


}
