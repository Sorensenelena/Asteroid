package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IPostEntityProcessingService {
    /**
     * <p>
     *     The method is called <b>AFTER</b> every frame and is used to update the behavior of the entities.
     *     The order of the entities being processed is <b>NOT</b> guaranteed.
     * </p>
     *
     * <p>Pre-conditions:</p>
     * <ul>
     *     <li>{@code gameData} isn't {@code null}.</li>
     *     <li>{@code world} isn't {@code null}.</li>
     *     <li>{@code world} contains {@code entities} that implement {@code IEntityProcessingService}.</li>
     * </ul>
     *
     * <p>Post-conditions:</p>
     * <ul>
     *     <li>All {@code entities} has been processed in the {@code world}.</li>
     * </ul>
     *
     * @param gameData GameData stores the metadata about the game’s state.
     * @param world World stores the entities of the game.
     */
    void process(GameData gameData, World world);
}
