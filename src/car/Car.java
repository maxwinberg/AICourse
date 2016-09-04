package car;
import driver.*;


/**
 * Created by MaxWinLaptop on 2016-09-04.
 */
public class Car {

    private String color;
    private int wheels;
    private float fast;
    private String model;
    private boolean driving;
    private Driver driver;



    public Car(String color, int wheels, float fast, Driver driver){
        this.color = color;

        this.wheels = wheels;
        this.fast = fast;
        model = "BMW";
        driving = false;
        this.driver = driver;
    }
    public void tellMeAboutYourself(){
        System.out.println("Color: " + color + " wheels: " + wheels + " Speed: " + fast + " is it driving?: " + driving + " the owner is: " + driver.getName());
    }

    public void drive(){
        driving = true;
    }

    public static void main(String[] args){

        Car car1 = new Car("Red", 4, 200.0f, new Human("Mike"));
        Car car2 = new Car("green", 3, 100.0f, new Robot());
        car1.tellMeAboutYourself();
        car2.tellMeAboutYourself();

        car2.drive();

        car1.tellMeAboutYourself();
        car2.tellMeAboutYourself();

    }
}
