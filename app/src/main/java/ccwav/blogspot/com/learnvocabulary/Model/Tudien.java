package ccwav.blogspot.com.learnvocabulary.Model;

import java.io.Serializable;

/**
 * Created by quang on 3/8/2016.
 */
public class Tudien implements Serializable {
    private int _id;
    private String word;
    private String mean;
    private String History;

    public Tudien() {
    }

    public int get_id() {
        return _id;
    }

    public String getWord() {
        return word;
    }

    public String getMean() {
        return mean;
    }

    public String getHistory() {
        return History;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public void setHistory(String history) {
        History = history;
    }
}
