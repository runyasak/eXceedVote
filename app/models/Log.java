package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.Date;
import java.util.List;


@Entity
public class Log extends Model {

    @Id
    public Long ID;

    public String username;

    public Date date;

    public static Finder<Long,Log> find=new Finder<Long,Log>(Long.class,Log.class);



}
