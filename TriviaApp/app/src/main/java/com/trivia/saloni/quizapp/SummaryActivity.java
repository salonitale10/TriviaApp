package com.trivia.saloni.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.trivia.saloni.quizapp.R;

public class SummaryActivity extends AppCompatActivity {

    TextView tv;
    ListView listView;
    Button finishButton;

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int game_Count=0;
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_summary);
        tv=(TextView) findViewById(R.id.username);
        tv.setText(tv.getText().toString() + " " + QuestionsActivity.userName);

        listView = (ListView) findViewById(R.id.questionList);

        QuestionListAdapter queryQuestionQuestionListAdapter = new QuestionListAdapter(this, QuestionsActivity.queryQuestions);
        listView.setAdapter(queryQuestionQuestionListAdapter);
        finishButton = (Button)findViewById(R.id.finish);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabaseHelper.addQueryData(QuestionsActivity.userName, QuestionsActivity.queryQuestions);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


}
