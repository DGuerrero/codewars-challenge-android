
package com.quoders.app.codewarschallenge.data.network.model.users;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Objc {

    @SerializedName("color")
    private String Color;
    @SerializedName("name")
    private String Name;
    @SerializedName("rank")
    private Long Rank;
    @SerializedName("score")
    private Long Score;

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getRank() {
        return Rank;
    }

    public void setRank(Long rank) {
        Rank = rank;
    }

    public Long getScore() {
        return Score;
    }

    public void setScore(Long score) {
        Score = score;
    }

}
