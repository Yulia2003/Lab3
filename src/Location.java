import java.util.Objects;

/**
 * This class represents a specific location in a 2D map. Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o == null || o.getClass() != this.getClass())
            return false;

        Location location = (Location) o;
        return xCoord == location.xCoord && yCoord == location.yCoord;
    }

    @Override
    public int hashCode() {
        int result = 11; // Простое число

    // используется другое простое число для перемножения
    //С помощью простого числа удается достичь более равномерного раброса значения по диапозону всех возможных значений
        result = 31 * result + xCoord;
        result = 31 * result + yCoord;
        return result;
    }
}