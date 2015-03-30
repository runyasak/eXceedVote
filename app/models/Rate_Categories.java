package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;


@Entity
public class Rate_Categories extends Model {

    @Id
    public Long ID;
    public int score;
    @OneToMany(mappedBy="rate_cate")
    public List<Rate> rates;
    @ManyToOne
    public Topic topics;
    public static Finder<Long,Rate_Categories> find=new Finder<Long,Rate_Categories>(Long.class,Rate_Categories.class);



}
