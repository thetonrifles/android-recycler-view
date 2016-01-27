package com.thetonrifles.recyclerviewsample.twitter;

class Tweet {

    private String author;
    private String content;

    public Tweet(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

}
