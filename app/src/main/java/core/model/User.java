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
    private String gender;
    private String token;
    private String displayName;
    private String avatar;
    private int uid;
    private long epochCreated;
    private boolean loginStatus = false;


    @Ignore
    private String provider;

    private static User user;

    public static User getInstance() {
        if(user == null) {
            user = new User();
        }
        return user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPasswordSHA() {
        return passwordSHA;
    }

    public void setPasswordSHA(String passwordSHA) {
        this.passwordSHA = passwordSHA;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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
