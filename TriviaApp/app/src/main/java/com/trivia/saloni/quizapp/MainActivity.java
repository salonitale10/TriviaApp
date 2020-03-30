package com.trivia.saloni.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.trivia.saloni.quizapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton=(Button)findViewById(R.id.button);
        Button historyButton = (Button)findViewById(R.id.history);

        final EditText nametext=(EditText)findViewById(R.id.editName);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nametext.getText().toString();
                Intent intent=new Intent(getApplicationContext(),QuestionsActivity.class);
                intent.putExtra("username",name);
                startActivity(intent);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

    }

}

