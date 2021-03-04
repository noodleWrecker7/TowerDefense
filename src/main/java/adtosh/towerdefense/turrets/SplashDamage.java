package adtosh.towerdefense.turrets;

import adtosh.towerdefense.App;
import adtosh.towerdefense.entity.Balloon;

import java.util.ArrayList;

public interface SplashDamage {

    default void dealSplashDamage(Balloon b, ArrayList<Balloon> splashedBalloons, int range){
        if (range<0)range*=-1;
        for (Balloon balloon : App.currentGame.getLevel().getBalloons()) {
            if (balloon.getX()> b.getX() -range && balloon.getX()< b.getX() +range) {
                if (balloon.getY() > b.getY() - range && balloon.getY() < b.getY() +range) {
                    splashedBalloons.add(balloon);

                }
            }

        }
    }
}
