package demo.utt37.congcau.apptracnghiem.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cong on 1/29/2018.
 */
public class QuestionModel implements Serializable,Parcelable{
    private int id;
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String result;
    private String num_exam;
    private String image;
    private String subject;
    private String answer;
    public int choiceID = -1;
    private String teacherCode;
    private String level;

    public QuestionModel(int id, String question, String ans_a, String ans_b, String ans_c, String result, String num_exam, String image, String subject, String answer) {
        this.id = id;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.result = result;
        this.num_exam = num_exam;
        this.image = image;
        this.subject = subject;
        this.answer = answer;
        this.choiceID = choiceID;
    }

    public QuestionModel() {
    }

    protected QuestionModel(Parcel in) {
        id = in.readInt();
        question = in.readString();
        ans_a = in.readString();
        ans_b = in.readString();
        ans_c = in.readString();
        result = in.readString();
        num_exam = in.readString();
        image = in.readString();
        subject = in.readString();
        answer = in.readString();
        choiceID = in.readInt();
        teacherCode = in.readString();
        level = in.readString();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns_a() {
        return ans_a;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getNum_exam() {
        return num_exam;
    }

    public void setNum_exam(String num_exam) {
        this.num_exam = num_exam;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public int getChoiceID() {
        return choiceID;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(question);
        parcel.writeString(ans_a);
        parcel.writeString(ans_b);
        parcel.writeString(ans_c);
        parcel.writeString(result);
        parcel.writeString(num_exam);
        parcel.writeString(image);
        parcel.writeString(subject);
        parcel.writeString(answer);
        parcel.writeInt(choiceID);
        parcel.writeString(teacherCode);
        parcel.writeString(level);
    }
}