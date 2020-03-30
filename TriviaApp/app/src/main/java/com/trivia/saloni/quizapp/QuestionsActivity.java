package com.trivia.saloni.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.trivia.saloni.quizapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {

    static List<QueryQuestion> queryQuestions;
    TextView tv;
    Button submitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    CheckBox ch1,ch2,ch3,ch4;
    static int flag=0;
    static String userName;

    public QuestionsActivity() {
        super();
        if(queryQuestions==null) {
            queryQuestions = new ArrayList<>();
            queryQuestions.add(makeFirstQuestion());
            queryQuestions.add(makeSecondQuestion());
        }
    }

    private QueryQuestion makeFirstQuestion() {
        QueryQuestion queryQuestion = new QueryQuestion();
        queryQuestion.setQuestion("Who is the best cricketer in the world?");
        queryQuestion.setOptions(Arrays.asList("Sachin Tendulkar", "Virat Kohli", "Adam Gilchirst", "Jacques Kallis"));
        queryQuestion.setSingleAnswer(true);
        return queryQuestion;
    }

    private QueryQuestion makeSecondQuestion() {
        QueryQuestion queryQuestion = new QueryQuestion();
        queryQuestion.setQuestion("What are the colors in the Indian national flag?");
        queryQuestion.setOptions(Arrays.asList("White", "Yellow", "Orange", "Green"));
        queryQuestion.setSingleAnswer(false);
        return queryQuestion;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(flag == 0) {
            setContentView(R.layout.activity_questions_single_answer);
            submitbutton=(Button)findViewById(R.id.button3);
            tv=(TextView) findViewById(R.id.tvque);
            radio_g=(RadioGroup)findViewById(R.id.answersGroup);
            rb1=(RadioButton)findViewById(R.id.radioButton);
            rb2=(RadioButton)findViewById(R.id.radioButton2);
            rb3=(RadioButton)findViewById(R.id.radioButton3);
            rb4=(RadioButton)findViewById(R.id.radioButton4);

            QueryQuestion queryQuestion = queryQuestions.get(0);
            tv.setText(queryQuestion.getQuestion());
            rb1.setText(queryQuestion.getOptions().get(0));
            rb2.setText(queryQuestion.getOptions().get(1));
            rb3.setText(queryQuestion.getOptions().get(2));
            rb4.setText(queryQuestion.getOptions().get(3));

            Intent intent = getIntent();
            String name= intent.getStringExtra("username");
            userName = name;
        }
        else {
            setContentView(R.layout.activity_questions_multiple_answer);
            submitbutton=(Button)findViewById(R.id.button3);
            tv=(TextView) findViewById(R.id.tvque);
            ch1=(CheckBox) findViewById(R.id.radioButton);
            ch2=(CheckBox)findViewById(R.id.radioButton2);
            ch3=(CheckBox)findViewById(R.id.radioButton3);
            ch4=(CheckBox)findViewById(R.id.radioButton4);

            QueryQuestion queryQuestion = queryQuestions.get(1);
            tv.setText(queryQuestion.getQuestion());
            ch1.setText(queryQuestion.getOptions().get(0));
            ch2.setText(queryQuestion.getOptions().get(1));
            ch3.setText(queryQuestion.getOptions().get(2));
            ch4.setText(queryQuestion.getOptions().get(3));
        }


        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("++"+queryQuestions.get(0).getSelectedAnswer());
                System.out.println("=="+queryQuestions.get(1).getSelectedAnswer());
                if(flag == 0)
                {
                    if(radio_g.getCheckedRadioButtonId()==-1)
                    {
                        Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                    String ansText = uans.getText().toString();
                    queryQuestions.get(0).setSelectedAnswer(ansText);
                    flag = 1;
                    Intent in = new Intent(getApplicationContext(),QuestionsActivity.class);
                    startActivity(in);
                }
                else {
                    StringBuilder ansText = new StringBuilder();
                    ansText.append(ch1.isChecked()?ch1.getText().toString()+", ":"");
                    ansText.append(ch2.isChecked()?ch2.getText().toString()+", ":"");
                    ansText.append(ch3.isChecked()?ch3.getText().toString()+", ":"");
                    ansText.append(ch4.isChecked()?ch4.getText().toString()+", ":"");

                    if(ansText.length()>0) {
                        ansText.delete(ansText.lastIndexOf(", "), ansText.length());
                    }
                    String answer = ansText.toString();
                    if(answer.length()==0)
                    {
                        Toast.makeText(getApplicationContext(), "Please select at-least one choice", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    flag = 0;
                    queryQuestions.get(1).setSelectedAnswer(answer);
                    Intent in = new Intent(getApplicationContext(),SummaryActivity.class);
                    startActivity(in);
                }

            }
        });
    }

}