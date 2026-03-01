package com.example.flashcardapp;

public class Flashcard {

    String question;
    String answer;

    public Flashcard(String q,String a){
        question=q;
        answer=a;
    }

    public String getQuestion(){ return question; }
    public String getAnswer(){ return answer; }

    public void setQuestion(String q){ question=q; }
    public void setAnswer(String a){ answer=a; }
}