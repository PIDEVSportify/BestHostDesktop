package Entities;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class Token {

    private  String token;
    private Timestamp created_at;
    private  Timestamp expires_at;

    public Token (String email)
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        this.token=encoder.encodeToString(bytes);
        this.created_at=   new Timestamp(System.currentTimeMillis());
        this.expires_at = new Timestamp(this.created_at.getTime()+ TimeUnit.MINUTES.toMillis(10));
    }

    public Token ()
    {

    }


    public void setToken(String token) {
        this.token = token;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setExpires_at(Timestamp expires_at) {
        this.expires_at = expires_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getExpires_at() {
        return expires_at;
    }

    public String getToken() {
        return token;
    }
}
