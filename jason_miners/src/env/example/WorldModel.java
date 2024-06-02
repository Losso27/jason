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

    public WorldModel() throws Exception {
        super(21, 21, 4);
        this.setDepot(5, 7);
        this.setAgPos(0, 1, 0);
        this.setAgPos(1, 20, 0);
        this.setAgPos(2, 3, 20);
        this.setAgPos(3, 20, 20);
        this.setGold(10,10);
    }
    public void setDepot(int x, int y) {
        depot = new Location(x, y);
        data[x][y] = DEPOT;
    }

    public void setGold(int x, int y) {
        data[x][y] = GOLD;
    }

}