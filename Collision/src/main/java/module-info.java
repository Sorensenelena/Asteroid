import dk.sdu.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    requires CommonAsteroids;
    requires CommonEnemy;
    requires CommonBullet;
    requires CommonPlayer;
    requires java.net.http;
    provides IPostEntityProcessingService with dk.sdu.collisionsystem.CollisionDetector;
}