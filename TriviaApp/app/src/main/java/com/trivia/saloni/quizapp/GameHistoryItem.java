package com.trivia.saloni.quizapp;

import java.util.List;

public class GameHistoryItem {
    private String userName;
    private List<QueryQuestion> queryQuestions;

    public int getGame_count() {
        return game_count;
    }

    public void setGame_count(int game_count) {
        this.game_count = game_count;
    }

    private int game_count;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<QueryQuestion> getQueryQuestions() {
        return queryQuestions;
    }

    public void setQueryQuestions(List<QueryQuestion> queryQuestions) {
        this.queryQuestions = queryQuestions;
    }
}
