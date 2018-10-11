package demo.utt37.congcau.apptracnghiem.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseTest {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("ans_a")
    @Expose
    private String ans_a;
    @SerializedName("ans_b")
    @Expose
    private String ans_b;
    @SerializedName("ans_c")
    @Expose
    private String ans_c;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("num_exam")
    @Expose
    private String num_exam;
    private String image;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("answer")
    @Expose
    private String answer;
    public int choiceID = -1;
    @SerializedName("teacher_code")
    @Expose
    private String teacher_code;
    @SerializedName("level")
    @Expose
    private String level;

    public ResponseTest() {
    }

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

    public int getChoiceID() {
        return choiceID;
    }

    public void setChoiceID(int choiceID) {
        this.choiceID = choiceID;
    }

    public String getteacher_code() {
        return teacher_code;
    }

    public void setteacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
