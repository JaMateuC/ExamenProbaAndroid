package mateu.jaume.examenproba.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import mateu.jaume.examenproba.R;
import model.Follower;
import model.FollowerList;
import model.Tarjeta;
import rest.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Result extends AppCompatActivity {

    public static final String BASE_URL = "https://api.github.com/users/";
    private Retrofit retrofit = null;
    private Retrofit retrofit2 = null;

    private String nombreRepo;
    private ArrayList<Follower> followerList;
    private ArrayList<String> listaImage;
    private ArrayList<String> listaLogin;
    private String userAvatar;
    private int userFoll;
    private int userRepo;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle extras = getIntent().getExtras();

        nombreRepo = extras.getString("repoNombre");

        followerList = new ArrayList<>();
        listaImage = new ArrayList<>();
        listaLogin = new ArrayList<>();



        getActivityResults();

    }

    public void getActivityResults(){

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Rest RestApiService = retrofit.create(Rest.class);

        Call<ArrayList<Follower>> call = RestApiService.getFollowers(nombreRepo);
        String a = call.request().url().toString();
        call.enqueue(new Callback<ArrayList<Follower>>() {
            @Override
            public void onResponse(Call<ArrayList<Follower>> call, Response<ArrayList<Follower>> response) {
                if(response.isSuccessful()){
                    followerList.addAll(response.body());
                    getActivityResults2();
                }else{
                    Toast.makeText(getBaseContext(), "Error: No existe el usuario", Toast.LENGTH_LONG).show();
                    finish();
                }


            }
            @Override
            public void onFailure(Call<ArrayList<Follower>> call, Throwable throwable) {
                Toast.makeText(getBaseContext(), "Error: No hay conexion con la API", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public void getActivityResults2() {

        if (retrofit2 == null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Rest RestApiService = retrofit2.create(Rest.class);

        Call<Follower> call = RestApiService.getUser(nombreRepo);
        call.enqueue(new Callback<Follower>() {
            @Override
            public void onResponse(Call<Follower> call, Response<Follower> response) {
                if(response.isSuccessful()) {
                    userAvatar = response.body().getAvatar_url();
                    userFoll = response.body().getFollowing();
                    userRepo = response.body().getPublic_repos();
                    createList();
                }else{
                    Toast.makeText(getBaseContext(), "Error: No existe el usuario", Toast.LENGTH_LONG).show();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<Follower> call, Throwable throwable) {
                Toast.makeText(getBaseContext(), "Error: No hay conexion con la API", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }

    public void createList(){

        TextView textRepo = (TextView) findViewById(R.id.textView2);
        TextView textFollow = (TextView)  findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        textFollow.setText("Followers: " + Integer.toString(userFoll));
        textRepo.setText("Repositories: " + Integer.toString(userRepo));

        Picasso.with(this)
                .load(userAvatar)
                .into(imageView);

        if(followerList.isEmpty()){

            Toast.makeText(getBaseContext(), "Info: no tiene followers", Toast.LENGTH_LONG).show();
        }else {

            mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new FollowersAdapter(this, followerList);
            mRecyclerView.setAdapter(mAdapter);
        }

    }
}
