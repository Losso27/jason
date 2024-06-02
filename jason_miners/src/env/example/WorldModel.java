package example;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class WorldModel extends GridWorldModel {

    public static final int   GOLD  = 16;
    public static final int   DEPOT = 32;

    Location                  depot;

    public void setDepot(int x, int y) {
        depot = new Location(x, y);
        data[x][y] = DEPOT;
    }

    public void setGold(int x, int y) {
        data[x][y] = GOLD;
    }

    static WorldModel world() throws Exception {
        super(21, 21, 4);
        model.setDepot(5, 7);
        model.setAgPos(0, 1, 0);
        model.setAgPos(1, 20, 0);
        model.setAgPos(2, 3, 20);
        model.setAgPos(3, 20, 20);
        model.setGold(10,10);
        return model;
    }

}