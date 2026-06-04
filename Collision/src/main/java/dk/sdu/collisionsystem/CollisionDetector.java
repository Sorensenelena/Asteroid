package dk.sdu.collisionsystem;

import dk.sdu.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.asteroids.Asteroid;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.bullet.Bullet;
import dk.sdu.cbse.common.player.Player;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    HttpClient client = HttpClient.newHttpClient();

    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                if (entity1.getID().equals(entity2.getID())) {
                    continue;
                }

                if (collides(entity1, entity2)) {

                    if (entity1 instanceof Asteroid) {
                        if (entity2 instanceof Player) {
                            entity2.setHealth(0);
                            return;
                        }
                    }

                    if (entity1 instanceof Enemy) {
                        if (entity2 instanceof Player) {
                            entity2.setHealth(0);
                            return;
                        }
                    }

                    if (entity1 instanceof Bullet) {
                        if (((Bullet) entity1).getShooter() instanceof Player) {
                            if (entity2 instanceof Asteroid) {
                                entity2.setHealth(entity2.getHealth() - 1);
                                ((Asteroid) entity2).setHit(true);
                                world.removeEntity(entity1);
                                incrementScore(1); // added
                            }
                            if (entity2 instanceof Enemy) {
                                entity2.setHealth(entity2.getHealth() - 1);
                                if (entity2.getHealth() <= 0){
                                    world.removeEntity(entity2);
                                }
                                world.removeEntity(entity1);
                            }
                        }
                        if (((Bullet) entity1).getShooter() instanceof Enemy) {
                            if (entity2 instanceof Player) {
                                entity2.setHealth(entity2.getHealth() - 1);
                                if (entity2.getHealth() <= 0){
                                    world.removeEntity(entity2);
                                }
                                world.removeEntity(entity1);
                            }
                        }
                    }
                }
            }
        }
    }

    private void incrementScore(int i) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/score/add/" + i))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }
}