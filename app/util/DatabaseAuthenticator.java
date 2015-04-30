package util;
import models.Account;
/**
 * Created by R7 on 4/30/2015.
 */
public class DatabaseAuthenticator extends Authenticator {
    private static DatabaseAuthenticator dba = null;


    public Account authenticate(String username,String password){
        Account account = Account.findAccount(username);
        if(account==null) return null;
        if(account.password.equals(password))return account;
        return null;

    }
}
