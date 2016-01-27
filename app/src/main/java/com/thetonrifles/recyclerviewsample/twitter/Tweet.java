package com.thetonrifles.recyclerviewsample.twitter;

import com.google.gson.annotations.SerializedName;

class Tweet {

    @SerializedName("text")
    private String content;

    public String getContent() {
        return content;
    }

}
