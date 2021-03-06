package adtosh.towerdefense.levels;

import adtosh.towerdefense.entity.Balloon;

public final class UnSpawnedBalloon {

    private int layers;
    private double spawnTimeMark;

    public UnSpawnedBalloon(int layers, double timeMark) {
        this.layers = layers;
        this.spawnTimeMark = timeMark;
    }

    public void createBalloon(){
        new Balloon(layers, "balloon-0");
    }

    public double getSpawnTimeMark() {
        return spawnTimeMark;
    }
}


