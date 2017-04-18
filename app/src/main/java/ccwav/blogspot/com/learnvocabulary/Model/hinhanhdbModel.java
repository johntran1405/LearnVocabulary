package ccwav.blogspot.com.learnvocabulary.Model;

public class hinhanhdbModel {
    private int id;
    private String name;
    private String picture;
    private String answertrue;

    public hinhanhdbModel(int id, String name, String picture, String answertrue) {
        this.id = id;
        this.name = name;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAnswertrue() {
        return answertrue;
    }

    public void setAnswertrue(String answertrue) {
        this.answertrue = answertrue;
    }
}
