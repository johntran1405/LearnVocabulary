package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by THANH LUAN on 16/01/2016.
 */
public class chudedbModel {
    private int id;
    private String name;
    private String picture1;
    private String picture2;
    private String picture3;
    private String picture4;
    private String picture5;
    private String picture6;
    private String picturetrue1;
    private String picturetrue2;
    private String answertrue;

    public chudedbModel(int id, String name, String picture1, String picture2, String picture3, String picture4, String picture5, String picture6, String picturetrue1, String picturetrue2, String answertrue) {
        this.id = id;
        this.name = name;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
        this.picture5 = picture5;
        this.picture6 = picture6;
        this.picturetrue1 = picturetrue1;
        this.picturetrue2 = picturetrue2;
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

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getPicture4() {
        return picture4;
    }

    public void setPicture4(String picture4) {
        this.picture4 = picture4;
    }

    public String getPicture5() {
        return picture5;
    }

    public void setPicture5(String picture5) {
        this.picture5 = picture5;
    }

    public String getPicture6() {
        return picture6;
    }

    public void setPicture6(String picture6) {
        this.picture6 = picture6;
    }

    public String getPicturetrue1() {
        return picturetrue1;
    }

    public void setPicturetrue1(String picturetrue1) {
        this.picturetrue1 = picturetrue1;
    }

    public String getPicturetrue2() {
        return picturetrue2;
    }

    public void setPicturetrue2(String picturetrue2) {
        this.picturetrue2 = picturetrue2;
    }

    public String getAnswertrue() {
        return answertrue;
    }

    public void setAnswertrue(String answertrue) {
        this.answertrue = answertrue;
    }
}
