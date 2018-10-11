package demo.utt37.congcau.apptracnghiem.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseScore {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("std_code")
    @Expose
    String std_code;
    @SerializedName("std_name")
    @Expose
    String std_name;
    @SerializedName("score")
    @Expose
    String score;

    public ResponseScore() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStd_code() {
        return std_code;
    }

    public void setStd_code(String std_code) {
        this.std_code = std_code;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
