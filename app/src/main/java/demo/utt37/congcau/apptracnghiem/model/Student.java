package demo.utt37.congcau.apptracnghiem.model;

public class Student {
    String id;
    String stdName;
    String stdCode;
    String std_add;
    String stdPhone;
    String stdBirthday;
    String stdClass;
    String stdGender;

    public Student() {
    }

    public Student(String id, String stdName, String stdCode, String std_add, String stdPhone, String stdBirthday, String stdClass) {
        this.id = id;
        this.stdName = stdName;
        this.stdCode = stdCode;
        this.std_add = std_add;
        this.stdPhone = stdPhone;
        this.stdBirthday = stdBirthday;
        this.stdClass = stdClass;
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
