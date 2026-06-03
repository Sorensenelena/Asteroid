package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.asteroids.Asteroid;

public class AsteroidSplitterImpl implements IAsteroidSplitter {

    @Override
    public void createSplitAsteroid(Entity e, World w) {
        System.out.println("Splitting.");
        w.addEntity(createFragment(e.getRotation() + 35, e.getRadius(), e.getHealth(), e.getX(), e.getY()));
        w.addEntity(createFragment(e.getRotation() - 35, e.getRadius(), e.getHealth(), e.getX(), e.getY()));
        w.removeEntity(e);
    }

    private Entity createFragment(double v, float size, int health, double x, double y) {
        Entity asteroid = new Asteroid();
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setHealth(health);
        asteroid.setRadius(size-1);
        asteroid.setRotation(v);
        asteroid.setMoveSpeed(100);
        return asteroid;
    }
}
