package example;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class WorldModel extends GridWorldModel {

    public static final int   GOLD  = 16;

    Set<Integer>              agWithGold;

    public WorldModel() throws Exception {
        super(21, 21, 4);
        agWithGold = new HashSet<Integer>();
        this.setAgPos(0, 1, 0);
        this.setAgPos(1, 20, 0);
        this.setAgPos(2, 3, 20);
        this.setAgPos(3, 20, 20);
        this.setGold(10,10);
    }

    public void setGold(int x, int y) {
        data[x][y] = GOLD;
    }

    public boolean isCarryingGold(int ag) {
        return agWithGold.contains(ag);
    }

    public void setAgCarryingGold(int ag) {
        agWithGold.add(ag);
    }
    public void setAgNotCarryingGold(int ag) {
        agWithGold.remove(ag);
    }

}