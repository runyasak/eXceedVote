package models;

import javax.persistence.*;
import play.*;
import play.db.ebean.Model;
import play.mvc.*;
import views.html.*;
import java.util.List;


@Entity
public class Images extends Model {
	
    @Id
    public Long ID;
	public String imageName;
}
