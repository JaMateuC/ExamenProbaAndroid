package rest;

import java.util.ArrayList;

import model.Follower;
import model.FollowerList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by root on 16/12/17.
 */

public interface Rest {

    @GET("{usuario}/followers")
    Call<ArrayList<Follower>> getFollowers(@Path("usuario") String usuario);

    @GET("{usuario}")
    Call<Follower> getUser(@Path("usuario") String usuario);

}
