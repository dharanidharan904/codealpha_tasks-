package com.example.random_quote;

public class QuoteModel {

    String quote;
    String author;

    public QuoteModel(String quote, String author) {
        this.quote = quote;
        this.author = author;
    }

    public String getQuote() {
        return quote;
    }

    public String getAuthor() {
        return author;
    }
}