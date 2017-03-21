package ccwav.blogspot.com.learnvocabulary.Model;

/**
 * Created by John on 3/18/2017.
 */

public class Categories_Model {


    private int Categories_ID;
    private static String Home;
    private static String People;
    private static String Animal;
    private static String Fruit;
    private static String Travel;
    private static String Sport;
    private static String Vegetable;
    private static String Food;

    public Categories_Model(int Categories_Id,String home,String people, String animal, String fruit,String travel,String sport,String vegetable,String food) {
        this.Categories_ID =Categories_Id;
        this.Home = home;
        this.People = people;
        this.Animal = animal;
        this.Fruit = fruit;
        this.Travel = travel;
        this.Sport = sport;
        this.Vegetable = vegetable;
        this.Food = food;
    }
    public int getCategories_ID() {
        return Categories_ID;
    }

    public void setCategories_ID(int categories_ID) {
        Categories_ID = categories_ID;
    }

    public static String getHome() {
        return Home;
    }

    public static void setHome(String home) {
        Home = home;
    }

    public static String getPeople() {
        return People;
    }

    public static void setPeople(String people) {
        People = people;
    }

    public static String getAnimal() {
        return Animal;
    }

    public static void setAnimal(String animal) {
        Animal = animal;
    }

    public static String getFruit() {
        return Fruit;
    }

    public static void setFruit(String fruit) {
        Fruit = fruit;
    }

    public static String getTravel() {
        return Travel;
    }

    public static void setTravel(String travel) {
        Travel = travel;
    }

    public static String getSport() {
        return Sport;
    }

    public static void setSport(String sport) {
        Sport = sport;
    }

    public static String getVegetable() {
        return Vegetable;
    }

    public static void setVegetable(String vegetable) {
        Vegetable = vegetable;
    }

    public static String getFood() {
        return Food;
    }

    public static void setFood(String food) {
        Food = food;
    }
}
