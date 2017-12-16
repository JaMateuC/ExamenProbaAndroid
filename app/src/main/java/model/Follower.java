package model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 16/12/17.
 */

public class Follower {

    @SerializedName("login")
    private String login;
    @SerializedName("avatar_url")
    private String avatar_url;
    @SerializedName("public_repos")
    private int public_repos;
    @SerializedName("following")
    private int following;

    public Follower(String login, String avatar_url, int public_repos, int following) {
        this.login = login;
        this.avatar_url = avatar_url;
        this.public_repos = public_repos;
        this.following = following;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }
}
