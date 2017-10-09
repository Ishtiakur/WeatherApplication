package Model;

/**
 * Created by Ishtiak on 10/6/2017.
 */

public class Weather {
    public Places places;
    public String iconData;
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();
    public Snow snow = new Snow();
    public Clouds clouds = new Clouds();

}
