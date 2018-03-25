
package com.quoders.app.codewarschallenge.data.network.model.users;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Ranks {

    @SerializedName("languages")
    private Languages Languages;
    @SerializedName("overall")
    private Overall Overall;

    public Languages getLanguages() {
        return Languages;
    }

    public void setLanguages(Languages languages) {
        Languages = languages;
    }

    public Overall getOverall() {
        return Overall;
    }

    public void setOverall(Overall overall) {
        Overall = overall;
    }

}
