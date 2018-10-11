package demo.utt37.congcau.apptracnghiem.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseTeacher {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("tch_name")
    @Expose
    String tchName;
    @SerializedName("tch_code")
    @Expose
    String tchCode;
    @SerializedName("tch_add")
    @Expose
    String tchAdd;
    @SerializedName("tch_phone")
    @Expose
    String tchPhone;
    @SerializedName("tch_birthday")
    @Expose
    String tchBirthday;
    @SerializedName("tch_gender")
    @Expose
    String tchGender;

    public ResponseTeacher() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTchName() {
        return tchName;
    }

    public void setTchName(String tchName) {
        this.tchName = tchName;
    }

    public String getTchCode() {
        return tchCode;
    }

    public void setTchCode(String tchCode) {
        this.tchCode = tchCode;
    }

    public String getTchAdd() {
        return tchAdd;
    }

    public void setTchAdd(String tchAdd) {
        this.tchAdd = tchAdd;
    }

    public String getTchPhone() {
        return tchPhone;
    }

    public void setTchPhone(String tchPhone) {
        this.tchPhone = tchPhone;
    }

    public String getTchBirthday() {
        return tchBirthday;
    }

    public void setTchBirthday(String tchBirthday) {
        this.tchBirthday = tchBirthday;
    }

    public String getTchGender() {
        return tchGender;
    }

    public void setTchGender(String tchGender) {
        this.tchGender = tchGender;
    }
}
