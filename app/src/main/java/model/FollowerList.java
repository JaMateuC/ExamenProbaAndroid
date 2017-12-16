package model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by root on 16/12/17.
 */

public class FollowerList {

    @SerializedName("result")
    private ArrayList<Follower> result;

    public FollowerList(ArrayList<Follower> result) {
        this.result = result;
    }

    public ArrayList<Follower> getResult() {
        return result;
    }

    public void setResult(ArrayList<Follower> result) {
        this.result = result;
    }
}
