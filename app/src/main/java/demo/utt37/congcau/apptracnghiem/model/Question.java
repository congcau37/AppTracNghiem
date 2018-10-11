package demo.utt37.congcau.apptracnghiem.model;

/**
 * Created by cong on 1/29/2018.
 */
public class Question {
    private int id;
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String result;
    private int num_exam;
    private String image;
    private String subject;
    private String answer;

    public Question(String question, String ans_a, String ans_b, String ans_c, String result, int num_exam, String image, String subject) {
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b = ans_b;
        this.ans_c = ans_c;
        this.result = result;
        this.num_exam = num_exam;
        this.image = image;
        this.subject = subject;
    }

    public int choiceID = -1;

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

    public int getNum_exam() {
        return num_exam;
    }

    public void setNum_exam(int num_exam) {
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
}