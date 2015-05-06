package utils;
import models.Account;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by R7 on 4/30/2015.
 */
public class DatabaseAuthenticator extends Authenticator {
    private static DatabaseAuthenticator dba = null;
    public static DatabaseAuthenticator newInstance() {
        if(dba == null) {
            dba = new DatabaseAuthenticator();
        }
        return dba;
    }


    public   Account authenticate(String username,String password){

        Account user_account = Account.findAccount(username);

        if (user_account != null && BCrypt.checkpw(password, user_account.password)) {
            return user_account;
        } else {
            return null;
        }

    }
}