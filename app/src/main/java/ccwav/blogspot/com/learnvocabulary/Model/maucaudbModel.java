package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by THANH LUAN on 10/01/2016.
 */
public class maucaudbModel {
    private int id;
    private String name;
    private String sentence;

    public maucaudbModel(int id, String name, String sentence) {
        this.id = id;
        this.name = name;
        this.sentence = sentence;
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

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
