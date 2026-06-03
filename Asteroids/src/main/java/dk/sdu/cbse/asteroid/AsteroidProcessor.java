package dk.sdu.cbse.asteroid;

import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;


public class AsteroidProcessor implements IEntityProcessingService {
    private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

    private static double spawnRate = 5;
    private static double spawnTimer = 0.0;

    @Override
    public void process(GameData gameData, World world) {

        spawnTimer += gameData.getDeltaTime();

        if (spawnTimer >= spawnRate) {
            spawnTimer -= spawnRate;

            world.addEntity(new AsteroidPlugin().createAsteroid(gameData));
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {

            if (asteroid.getHealth() <= 0) {
                world.removeEntity(asteroid);
                continue;
            }

            if (asteroidSplitter != null) {
                if (((Asteroid) asteroid).isHit()) {
                    System.out.println("Asteroid hit.");
                    System.out.println("Trying to split.");
                    asteroidSplitter.createSplitAsteroid(asteroid, world);
                    ((Asteroid) asteroid).setHit(false);
                }
            }

            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            if (asteroid.getX() < 0) {
                asteroid.setX(asteroid.getX() - gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(asteroid.getY() - gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }
        }
    }

}

