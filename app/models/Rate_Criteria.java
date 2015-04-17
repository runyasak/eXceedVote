package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;

@Entity
public class Rate_Criteria extends Model {

    @Id
    public Long ID;
    public String criteria_name;
    @OneToMany(mappedBy = "criteria")
    public List<Rate_Records> rate_rec;

    public static Finder<Long,Rate_Criteria> find=new Finder<Long,Rate_Criteria>(Long.class,Rate_Criteria.class);
    
    public static Rate_Criteria findTopic(String topic){
        return Rate_Criteria.find.where().eq("criteria_name", topic).findUnique();
    }

    public static Rate_Criteria findTopicID(Long topic_id){
        return Rate_Criteria.find.where().eq("ID", topic_id).findUnique();
    }


}
