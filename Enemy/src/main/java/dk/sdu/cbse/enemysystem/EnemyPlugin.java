package dk.sdu.cbse.enemysystem;

import dk.sdu.cbse.common.data.Entity;
import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;
import dk.sdu.cbse.common.services.IGamePluginService;
import javafx.scene.paint.Color;
import dk.sdu.cbse.common.enemy.Enemy;

public class EnemyPlugin implements IGamePluginService{

    private Entity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = new Enemy();
        enemy.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemy.setX(Math.random() * gameData.getDisplayWidth());
        enemy.setY(Math.random() * gameData.getDisplayHeight());
        enemy.setRotation(Math.random() * 360);
        enemy.setRadius(8);
        enemy.setHealth(3);
        enemy.setMoveSpeed(2);
        enemy.setColor(Color.RED);
        world.addEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }
}
