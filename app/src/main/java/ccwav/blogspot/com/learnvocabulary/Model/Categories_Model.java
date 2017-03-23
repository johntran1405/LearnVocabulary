package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by John on 3/18/2017.
 */

public class Categories_Model {


    private int Categori_ID;
    private String Categori_Name ;
    private String Categori_Icon;

    public Categories_Model()
    {

    }

    public Categories_Model(int categories_ID,String categori_Name, String categori_Icon) {
        Categori_ID = categories_ID;
        Categori_Name=categori_Name;
        Categori_Icon=categori_Icon;

    }

    public int getCategori_ID() {
        return Categori_ID;
    }

    public void setCategori_ID(int Categori_ID) {
        Categori_ID = Categori_ID;
    }

    public String getCategori_Name() {
        return Categori_Name;
    }

    public void setCategori_Name(String categori_Name) {
        Categori_Name = categori_Name;
    }

    public String getCategori_Icon() {
        return Categori_Icon;
    }

    public void setCategori_Icon(String categori_Icon) {
        Categori_Icon = categori_Icon;
    }





}
