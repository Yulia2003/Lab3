import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinWorkerThread;

/**
 *Этот класс хранит базовое состояние, необходимое для алгоритма A* для вычисления
 * * путь по карте. Это состояние включает в себя коллекцию "открытых путевых точек" и
 * * другую коллекцию "закрытых путевых точек". Кроме того, этот класс обеспечивает
 * * основные операции, необходимые алгоритму поиска пути A* для выполнения его
 * * обработка.
 **/
public class AStarState
{
    /** Это ссылка на карту, по которой перемещается алгоритм A*. **/
    private Map2D map;
    /**
     * вершины будут храниться в хэш-карте, где местоположение вершин является
     * ключом, а сами вершины являются значениями
     */
    private HashMap<Location, Waypoint> openWaypoints = new HashMap<>();
    private HashMap<Location, Waypoint> closeWaypoints = new HashMap<>();

    /**
     * Инициализируйте новый объект состояния для использования алгоритма поиска пути A*.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /**Возвращает карту, по которой перемещается навигатор A*. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * Этот метод сканирует все открытые путевые точки и возвращает путевую точку
     * * с минимальной общей стоимостью. Если открытых путевых точек нет, этот метод
     * * возвращает <код>null</code> TODO
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (numOpenWaypoints() == 0) {
            return null;
        }

        Waypoint minWaypoint = null;
        float min = Float.MAX_VALUE;

        for (Waypoint waypoint : openWaypoints.values()) {
            float cost = waypoint.getTotalCost();
            if (cost < min) {
                min = cost;
                minWaypoint = waypoint;
            }
        }
        return minWaypoint;
    }

    /**
     * Этот метод добавляет путевую точку к (или потенциально обновляет уже имеющуюся путевую точку
     * в) коллекция "открытые путевые точки". Если еще не существует открытого
     * * путевая точка в новом местоположении путевых точек, то новая путевая точка просто
     * добавлено в коллекцию. Однако, если в
     местоположении * * new waypoints уже есть путевая точка, новая путевая точка заменяет только старую <em>
     * * if</em> значение "предыдущей стоимости" новых путевых точек меньше текущего
     * * значение путевых точек "предыдущая стоимость".TODO
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Waypoint openWP = openWaypoints.get(newWP.loc);

        if (openWP == null || newWP.getPreviousCost() < openWP.getPreviousCost()) {
            openWaypoints.put(newWP.loc, newWP);
            return true;
        }
        return false;
    }


    /** Возвращает текущее количество открытых путевых точек.TODO **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     Этот метод перемещает путевую точку в указанном местоположении из
     открытого списка  в закрытый список.TODO
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = openWaypoints.remove(loc);
        if (openWaypoints != null) {
            closeWaypoints.put(loc, waypoint);
        }
    }

    /**
     Возвращает значение true, если коллекция закрытых путевых точек содержит путевую точку
      для указанного местоположения.TODO
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closeWaypoints.containsKey(loc);
    }
}