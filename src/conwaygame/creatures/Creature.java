package conwaygame.creatures;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static conwaygame.creatures.CreatureType.*;

public class Creature {
    Strategy strategy;
    StrategyFactory strategyFactory = new StrategyFactory();
    boolean alive = false;

    public void setStateBasedOnNeighbors(List<Creature> neighbors) {
        //Kill or resurrect the cell given its number of neighbours

        int aliveNeighborCount = countAliveCreatures(neighbors);
        if (isAlive() && (imTooLonely(aliveNeighborCount) || imOverCrowded(aliveNeighborCount))) {
            this.kill();
        } else if (aliveNeighborCount == this.strategy.getResurrectionNeighbourCount()) {
            //revive & decide what creature to revive as
            this.determineStrategyToResurrectWith(neighbors);
        }

    }

    private boolean imTooLonely(int aliveNeighborCount) {
        return aliveNeighborCount < this.strategy.getMinimumNeighbours();
    }

    private boolean imOverCrowded(int aliveNeighborCount) {
        return aliveNeighborCount > this.strategy.getMaxNeighbours();
    }

    private void determineStrategyToResurrectWith(List<Creature> creatures) {

        HashMap<CreatureType, Integer> strategyTypeCount = new HashMap<>();
        strategyTypeCount.put(DEFAULT, countCreaturesOfType(creatures, DEFAULT));
        strategyTypeCount.put(EXPLOSIVE, countCreaturesOfType(creatures, EXPLOSIVE));
        strategyTypeCount.put(SCARCITY, countCreaturesOfType(creatures, SCARCITY));

        CreatureType largestType = DEFAULT;
        int maxCount = strategyTypeCount.get(DEFAULT);
        for(Map.Entry<CreatureType, Integer> strategyCountPair : strategyTypeCount.entrySet()) {
            if (strategyCountPair.getValue() > maxCount) {
                largestType = strategyCountPair.getKey();
                maxCount = strategyCountPair.getValue();
            }
        }

        this.reviveWithStrategy(strategyFactory.getStrategy(largestType));
    }

    private int countAliveCreatures(List<Creature> creatures) {
        int aliveCreatureCount = 0;
        for (Creature creature : creatures) {
            if (creature.isAlive()) {
                aliveCreatureCount++;
            }
        }
        return aliveCreatureCount;
    }

    private int countCreaturesOfType(List<Creature> creatures, CreatureType type) {
        int creatureCount = 0;
        for (Creature creature : creatures) {
            if (creature.getType() == type) {
                creatureCount++;
            }
        }
        return creatureCount;
    }

    public Creature(Strategy strategy) {
        this.strategy = strategy;
    }

    public Creature() {
        this.strategy = strategyFactory.getDefaultStrategy();
    }

    public void makeAlive() {
        this.alive = true;
    }

    public void kill() {
        this.alive = false;
    }

    public void reviveWithStrategy(Strategy strategy) {
        this.makeAlive();
        this.strategy = strategy;
    }

    public boolean isDefault(){ return strategy.isDefault(); }
    public boolean isExplosive(){ return strategy.isExplosive(); }
    public boolean isScarcity(){ return strategy.isScarcity(); }
    public boolean isDead(){ return !this.alive; }
    public boolean isAlive() {return this.alive; }

    public Color getColor() {
        if (this.isAlive()) {
            return strategy.getAliveColor();
        } else {
            return strategy.getDeadColor();
        }
    }

    public CreatureType getType() { return strategy.getType();}

}