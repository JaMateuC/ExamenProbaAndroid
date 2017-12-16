package mateu.jaume.examenproba.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import mateu.jaume.examenproba.R;

public class GitHubFollowers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_followers);
        addButtonCercaListener();

    }

    public void addButtonCercaListener(){

        Button buttonCerca = (Button) findViewById(R.id.buttonC);
        buttonCerca.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent repoIntent = new Intent(v.getContext(),Result.class);
                EditText repoNombre = (EditText) findViewById(R.id.repoNombre);
                String repo = repoNombre.getText().toString();
                repoIntent.putExtra("repoNombre", repo);
                startActivityForResult(repoIntent,1);

            }
        });

    }
}
