package com.trivia.saloni.quizapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.trivia.saloni.quizapp.R;

import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends AppCompatActivity {

    private static final String TAG = "HistoryActivity";

    DatabaseHelper mDatabaseHelper;
    private ListView gameListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(this);
        setContentView(R.layout.activity_history);

        gameListView = (ListView) findViewById(R.id.gameList);

        List<GameHistoryItem> historyData = populateListView();
        createView(historyData);

       Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseHelper.addQueryData(QuestionsActivity.userName, QuestionsActivity.queryQuestions);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<GameHistoryItem> populateListView() {
        List<GameHistoryItem> historyData = new ArrayList<>();
        Cursor data = mDatabaseHelper.getUserData();
        while (data.moveToNext()) {
            historyData.add(createGameHistoryItem(Integer.parseInt(data.getString(0)), data.getString(1)));
        }
        return historyData;
    }

    private GameHistoryItem createGameHistoryItem(int userId, String userName) {
        GameHistoryItem gameHistoryItem = new GameHistoryItem();
        gameHistoryItem.setUserName(userName);

        List<QueryQuestion> queryQuestions = new ArrayList<>();
        Cursor data = mDatabaseHelper.getGameHistoryForUser(userId);


        while (data.moveToNext()) {
           QueryQuestion queryQuestion= new QueryQuestion();
            queryQuestion.setQuestion(data.getString(1));
            queryQuestion.setSelectedAnswer(data.getString(2));
            queryQuestions.add(queryQuestion);

        }
        gameHistoryItem.setQueryQuestions(queryQuestions);
        return gameHistoryItem;
    }

    private void createView(List<GameHistoryItem> historyData) {
        GameListAdapter gameListAdapter = new GameListAdapter(this, historyData);
        gameListView.setAdapter(gameListAdapter);
    }

}
