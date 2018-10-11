package demo.utt37.congcau.apptracnghiem.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseStudent {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("stdName")
    @Expose
    String stdName;
    @SerializedName("stdCode")
    @Expose
    String stdCode;
    @SerializedName("stdAdd")
    @Expose
    String std_add;
    @SerializedName("stdPhone")
    @Expose
    String stdPhone;
    @SerializedName("stdBirthday")
    @Expose
    String stdBirthday;
    @SerializedName("stdClass")
    @Expose
    String stdClass;
    @SerializedName("stdGender")
    @Expose
    String stdGender;

    public ResponseStudent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public String getStd_add() {
        return std_add;
    }

    public void setStd_add(String std_add) {
        this.std_add = std_add;
    }

    public String getStdPhone() {
        return stdPhone;
    }

    public void setStdPhone(String stdPhone) {
        this.stdPhone = stdPhone;
    }

    public String getStdBirthday() {
        return stdBirthday;
    }

    public void setStdBirthday(String stdBirthday) {
        this.stdBirthday = stdBirthday;
    }

    public String getStdClass() {
        return stdClass;
    }

    public void setStdClass(String stdClass) {
        this.stdClass = stdClass;
    }

    public String getStdGender() {
        return stdGender;
    }

    public void setStdGender(String stdGender) {
        this.stdGender = stdGender;
    }
}
