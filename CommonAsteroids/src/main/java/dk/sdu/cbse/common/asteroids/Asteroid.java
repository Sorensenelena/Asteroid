package dk.sdu.cbse.common.asteroids;

import dk.sdu.cbse.common.data.Entity;

public class Asteroid extends Entity {
    private boolean isHit;

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        this.isHit = hit;
    }
}
