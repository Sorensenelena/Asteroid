package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidSplitterTest {

    @Test
    public void testAsteroidSplits() {
        World world = new World();
        Entity asteroid = new Asteroid();
        asteroid.setHealth(2);
        asteroid.setRadius(10);
        world.addEntity(asteroid);

        AsteroidSplitterImpl splitter = new AsteroidSplitterImpl();
        splitter.createSplitAsteroid(asteroid, world);

        assertEquals(2, world.getEntities(Asteroid.class).size());
        assertFalse(world.getEntities().contains(asteroid));
    }
}