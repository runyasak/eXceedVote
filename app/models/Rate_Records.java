package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;


@Entity
public class Rate_Records extends Model {

    @Id
    public Long ID;
    public int score;
    @OneToMany(mappedBy="rate_rec", cascade = CascadeType.REMOVE)
    public List<Rate> rates;
    @ManyToOne
    public Rate_Criteria criteria;
    public static Finder<Long, Rate_Records> find=new Finder<Long, Rate_Records>(Long.class, Rate_Records.class);



}
