package dk.sdu.cbse.common.data;

import java.util.UUID;
import java.io.Serializable;
import javafx.scene.paint.Color;

public class Entity implements Serializable {
    private final UUID ID = UUID.randomUUID();

    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private float radius;
    private Color color;
    private int health;
    private int moveSpeed;
    private int turnSpeed;


    public String getID() {
        return ID.toString();
    }

    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color == null ? color = Color.BLACK : color;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {return health;}

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }
    public int getMoveSpeed() {return moveSpeed;}

    public void setTurnSpeed(int turnSpeed) {this.turnSpeed = turnSpeed;}

    public int getTurnSpeed() {return turnSpeed;}
}
