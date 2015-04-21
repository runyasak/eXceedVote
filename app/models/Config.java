package models;
import javax.persistence.*;

import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;

import java.util.Date;
import java.util.List;


@Entity
public class Config extends Model {
    public final static long main=1;
    public final static long team=2;
    public final static long edit_profile=3;



    @Id
    public Long ID;

    public String page_name;

    public boolean open;


    public static Finder<Long,Config> find=new Finder<Long,Config>(Long.class,Config.class);

    public Config(String page_name,boolean open){
        this.open=open;
        this.page_name=page_name;

    }
    public Config(){


    }


}
