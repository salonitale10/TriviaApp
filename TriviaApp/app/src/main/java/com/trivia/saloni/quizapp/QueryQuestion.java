package com.trivia.saloni.quizapp;

import java.util.List;

public class QueryQuestion {
    private String question;
    private List<String> options;
    private boolean singleAnswer;
    private String selectedAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public boolean isSingleAnswer() {
        return singleAnswer;
    }

    public void setSingleAnswer(boolean singleAnswer) {
        this.singleAnswer = singleAnswer;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
