package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class Upload extends Controller {

	public static Result uploadImage(){
		MultipartFormData body = request().body().asMultipartFormData();
  		FilePart picture = body.getFile("picture");
  		BufferedImage bImage = null;
  		if (picture != null) {
		    String fileName = picture.getFilename();
		    String contentType = picture.getContentType(); 
		    File file = picture.getFile();
		    try {
		    	bImage = ImageIO.read(file);
		    	ImageIO.write(bImage, "jpg", new File("public/upload/"+fileName+".jpg"));
		    } catch (IOException e) {
               System.out.println("Exception occured :" + e.getMessage());
         	}
			return ok("File uploaded");
		} else {
		    flash("error", "Missing file");
		    return redirect(routes.Application.main());    
		}
	}

}