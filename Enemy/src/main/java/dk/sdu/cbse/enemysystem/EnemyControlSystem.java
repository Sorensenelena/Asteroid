package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.bullet.BulletSPI;
import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IEntityProcessingService;
import dk.sdu.cbse.common.enemy.Enemy;
import dk.sdu.cbse.common.player.Player;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class EnemyControlSystem implements IEntityProcessingService {

    private long lastShot = 0;
    private long lastTurn = 0;
    private long shootInterval = randomBetween(500, 2000);
    private long turnInterval = randomBetween(1000, 3000);

    @Override
    public void process(GameData gameData, World world) {
        long now = System.currentTimeMillis();

        for (Entity enemy : world.getEntities(Enemy.class)) {
            if (now - lastTurn > turnInterval) {
                aimAtPlayer(enemy, world);
                lastTurn = now;
                turnInterval = randomBetween(1000, 3000);
            }

            enemy.setX(enemy.getX() + Math.cos(Math.toRadians(enemy.getRotation())) * enemy.getMoveSpeed());
            enemy.setY(enemy.getY() + Math.sin(Math.toRadians(enemy.getRotation())) * enemy.getMoveSpeed());

            // screen wrapping
            if (enemy.getX() < 0) enemy.setX(gameData.getDisplayWidth());
            if (enemy.getX() > gameData.getDisplayWidth()) enemy.setX(0);
            if (enemy.getY() < 0) enemy.setY(gameData.getDisplayHeight());
            if (enemy.getY() > gameData.getDisplayHeight()) enemy.setY(0);

            if (now - lastShot > shootInterval) {
                getBulletSPIs().stream().findFirst().ifPresent(
                        spi -> world.addEntity(spi.createBullet(enemy, gameData))
                );
                lastShot = now;
                shootInterval = randomBetween(500, 2000);
            }
        }
    }

    private long randomBetween(int min, int max) {
        return min + (long) (Math.random() * (max - min));
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class)
                .stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private void aimAtPlayer(Entity enemy, World world) {
        for (Entity player : world.getEntities(Player.class)) {
            double dx = player.getX() - enemy.getX();
            double dy = player.getY() - enemy.getY();
            enemy.setRotation(Math.toDegrees(Math.atan2(dy, dx)));
        }
    }
}