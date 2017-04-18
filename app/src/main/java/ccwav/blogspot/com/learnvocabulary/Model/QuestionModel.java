package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by quang on 04/19/2017.
 */

public class QuestionModel {
    int id,level;
    String quest,ans;

    public QuestionModel(int id, String quest, String ans, int level) {
        this.id = id;
        this.level = level;
        this.quest = quest;
        this.ans = ans;
    }

    public QuestionModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

}
