package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by THANH LUAN on 14/01/2016.
 */
public class nhandangtudbModel {
    private int id;
    private String name;
    private String audio;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answertrue;

    public nhandangtudbModel(int id, String name, String audio, String answer1, String answer2, String answer3, String answer4, String answertrue) {
        this.id = id;
        this.name = name;
        this.audio = audio;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.answertrue = answertrue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public String getAnswertrue() {
        return answertrue;
    }

    public void setAnswertrue(String answertrue) {
        this.answertrue = answertrue;
    }
}
