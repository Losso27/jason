package example;

// Environment code for project jason_miners

import jason.asSyntax.*;
import jason.environment.*;
import jason.asSyntax.parser.*;
import jason.environment.grid.Location;

import java.util.logging.*;

public class Env extends Environment {

    private Logger logger = Logger.getLogger("jason_miners."+Env.class.getName());
    WorldModel  model;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);
        try {
            initWorld();
            addPercept(ASSyntax.parseLiteral("percept("+args[0]+")"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: "+action+", but not implemented!");
        if (true) { // you may improve this condition
             informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }

    public void initWorld() {
        try {
            model = new WorldModel(); 
            clearPercepts();
            addPercept(Literal.parseLiteral("gsize(" + model.getWidth() + "," + model.getHeight() + ")"));
            updateAgsPercept();
        } catch (Exception e) {
            logger.warning("Error creating world "+e);
        }
    }

    private void updateAgsPercept() {
        for (int i = 0; i < 3; i++) {
            updateAgPercept(i);
        }
    }

    private void updateAgPercept(int ag) {
        updateAgPercept("miner" + (ag + 1), ag);
    }

    private void updateAgPercept(String agName, int ag) {
        clearPercepts(agName);
        // its location
        Location l = model.getAgPos(ag);
        addPercept(agName, Literal.parseLiteral("pos(" + l.x + "," + l.y + ")"));

        if (model.isCarryingGold(ag)) {
            addPercept(agName, Literal.parseLiteral("carrying_gold"));
        }

        // what's around
        updateAgPercept(agName, l.x - 1, l.y - 1);
        updateAgPercept(agName, l.x - 1, l.y);
        updateAgPercept(agName, l.x - 1, l.y + 1);
        updateAgPercept(agName, l.x, l.y - 1);
        updateAgPercept(agName, l.x, l.y);
        updateAgPercept(agName, l.x, l.y + 1);
        updateAgPercept(agName, l.x + 1, l.y - 1);
        updateAgPercept(agName, l.x + 1, l.y);
        updateAgPercept(agName, l.x + 1, l.y + 1);
    }

    private void updateAgPercept(String agName, int x, int y) {
        if (model == null || !model.inGrid(x,y)) return;
        if (model.hasObject(WorldModel.GOLD, x, y)) {
            addPercept(agName, Literal.parseLiteral("cell(" + x + "," + y + ",gold)"));
        }
        if (model.hasObject(WorldModel.AGENT, x, y)) {
            addPercept(agName, Literal.parseLiteral("cell(" + x + "," + y + ",ally)"));
        }
    }

}