
package com.quoders.app.codewarschallenge.data.network.model.users;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class UserResponse {

    @SerializedName("clan")
    private String Clan;
    @SerializedName("codeChallenges")
    private CodeChallenges CodeChallenges;
    @SerializedName("honor")
    private Long Honor;
    @SerializedName("leaderboardPosition")
    private Long LeaderboardPosition;
    @SerializedName("name")
    private String Name;
    @SerializedName("ranks")
    private Ranks Ranks;
    @SerializedName("skills")
    private List<String> Skills;
    @SerializedName("username")
    private String Username;

    public String getClan() {
        return Clan;
    }

    public void setClan(String clan) {
        Clan = clan;
    }

    public CodeChallenges getCodeChallenges() {
        return CodeChallenges;
    }

    public void setCodeChallenges(CodeChallenges codeChallenges) {
        CodeChallenges = codeChallenges;
    }

    public Long getHonor() {
        return Honor;
    }

    public void setHonor(Long honor) {
        Honor = honor;
    }

    public Long getLeaderboardPosition() {
        return LeaderboardPosition;
    }

    public void setLeaderboardPosition(Long leaderboardPosition) {
        LeaderboardPosition = leaderboardPosition;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Ranks getRanks() {
        return Ranks;
    }

    public void setRanks(Ranks ranks) {
        Ranks = ranks;
    }

    public List<String> getSkills() {
        return Skills;
    }

    public void setSkills(List<String> skills) {
        Skills = skills;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

}
