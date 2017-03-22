package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by John on 3/18/2017.
 */

public class Categories_Model {


    private int Categories_ID;
    private static String Categori_Name ;
    private static String Categori_Icon;

    public Categories_Model()
    {

    }

    public Categories_Model(int categories_ID,String categori_Name, String categori_Icon) {
        Categories_ID = categories_ID;
        Categori_Name=categori_Name;
        Categori_Icon=categori_Icon;

    }

    public int getCategories_ID() {
        return Categories_ID;
    }

    public void setCategories_ID(int categories_ID) {
        Categories_ID = categories_ID;
    }

    public static String getCategori_Name() {
        return Categori_Name;
    }

    public static void setCategori_Name(String categori_Name) {
        Categori_Name = categori_Name;
    }

    public static String getCategori_Icon() {
        return Categori_Icon;
    }

    public static void setCategori_Icon(String categori_Icon) {
        Categori_Icon = categori_Icon;
    }





}
