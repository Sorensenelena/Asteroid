package dk.sdu.cbse.common.player;

import dk.sdu.cbse.common.data.Entity;


public class Player extends Entity {
    private int turnSpeed = 150;
    private int moveSpeed = 200;

    public int getTurnSpeed() {
        return turnSpeed;
    }
    public void setTurnSpeed(int turnSpeed) {
        this.turnSpeed = turnSpeed;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
}
