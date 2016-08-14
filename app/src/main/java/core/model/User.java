package core.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by arysuryawan on 5/15/16.
 */
public class User extends RealmObject {
    @PrimaryKey
    private String email;

    private String passwordSHA;
    private String loginStatus;
    private String token;
    private String displayName;
    private String uid;
    private String avatar;
    private long epochCreated;

    @Ignore
    private String provider;

    private static User user;

    public static User getInstance() {
        if(user == null) {
            user = new User();
        }
        return user;
    }

    public String getPasswordSHA() {
        return passwordSHA;
    }

    public void setPasswordSHA(String passwordSHA) {
        this.passwordSHA = passwordSHA;
    }

    public String getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public long getEpochCreated() {
        return epochCreated;
    }

    public void setEpochCreated(long epochCreated) {
        this.epochCreated = epochCreated;
    }
}
