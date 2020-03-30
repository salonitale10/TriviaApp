package com.trivia.saloni.quizapp;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.trivia.saloni.quizapp.R;

import java.util.List;

public class GameListAdapter extends ArrayAdapter<GameHistoryItem> {
    private final Context context;
    private final List<GameHistoryItem> gameHistoryItems;


    public GameListAdapter(Context context, List<GameHistoryItem> gameHistoryItems) {
        super(context, R.layout.game_list_row, gameHistoryItems);
        this.context = context;
        this.gameHistoryItems = gameHistoryItems;
    }

    @Override
    public int getCount() {
        return gameHistoryItems.size();
    }

    @Nullable
    @Override
    public GameHistoryItem getItem(int position) {
        return gameHistoryItems.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.game_list_row, parent, false);

        TextView userName = (TextView) rowView.findViewById(R.id.userName);
        ListView questionListView = (ListView) rowView.findViewById(R.id.questionListHistory);
        GameHistoryItem gameHistoryItem = gameHistoryItems.get(position);

        userName.setText(gameHistoryItem.getUserName());
        QuestionListAdapter questionListAdapter = new QuestionListAdapter(context, gameHistoryItem.getQueryQuestions());

        questionListView.setAdapter(questionListAdapter);
        return rowView;
    }

}
