package demo.utt37.congcau.apptracnghiem.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHistory {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("exam_code")
    @Expose
    String exam_code;
    @SerializedName("level")
    @Expose
    String level;
    @SerializedName("date")
    @Expose
    String date;
    @SerializedName("score")
    @Expose
    String score;
    @SerializedName("std_name")
    @Expose
    String std_name;
    @SerializedName("std_code")
    @Expose
    String std_code;

    public ResponseHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExam_code() {
        return exam_code;
    }

    public void setExam_code(String exam_code) {
        this.exam_code = exam_code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getStd_code() {
        return std_code;
    }

    public void setStd_code(String std_code) {
        this.std_code = std_code;
    }
}
