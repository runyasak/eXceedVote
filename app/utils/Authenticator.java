package utils;
import models.Account;
/**
 * Created by R7 on 4/30/2015.
 */
public  abstract class Authenticator {
    public static Authenticator getInstance() {
        String value = play.Play.application().configuration().getString("exceed.authenticator");
        Authenticator authenticator = null;
        try {
            authenticator = (Authenticator)Class.forName(value).newInstance();
        } catch (Exception e) {
            return null;
        }
        return authenticator;

    }
    public abstract Account authenticate(String username, String password);

}