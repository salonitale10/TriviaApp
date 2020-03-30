package com.trivia.saloni.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.trivia.saloni.quizapp.R;

import java.util.List;


public class QuestionListAdapter extends ArrayAdapter<QueryQuestion> {
    private final Context context;
    private final List<QueryQuestion> queryQuestionList;


    public QuestionListAdapter(Context context, List<QueryQuestion> queryQuestionList) {
        super(context, R.layout.question_row, queryQuestionList);
        this.context = context;
        this.queryQuestionList = queryQuestionList;
    }

    @Override
    public int getCount() {
        return queryQuestionList.size();
    }

    @Override
    public QueryQuestion getItem(int i) {
        return queryQuestionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.question_row, parent, false);

        TextView question = (TextView) rowView.findViewById(R.id.question);
        TextView answer = (TextView) rowView.findViewById(R.id.answer);

        QueryQuestion queryQuestion = queryQuestionList.get(position);

            question.setText(queryQuestion.getQuestion());
            answer.setText("Answer: " + queryQuestion.getSelectedAnswer());

            return rowView;
    }
}
