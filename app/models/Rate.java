package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.List;


@Entity
public class Rate extends Model {

    @Id
    public Long ID;
    @ManyToOne
    public Account users;
    @ManyToOne
    public Team teams;
    @ManyToOne
    public Rate_Records rate_rec;

    public static Finder<Long,Rate> find=new Finder<Long,Rate>(Long.class,Rate.class);



}
