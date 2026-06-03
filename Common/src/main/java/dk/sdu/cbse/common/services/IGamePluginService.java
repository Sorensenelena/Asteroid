package dk.sdu.cbse.common.services;

import dk.sdu.cbse.common.data.GameData;
import dk.sdu.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * <p>
     *     The method is called when the game starts.
     *     The plugin is instantiated and put into the world.
     * </p>
     *
     * <p>Pre-conditions:</p>
     * <ul>
     *     <li>{@code gameData} isn't {@code null}.</li>
     *     <li>{@code world} isn't {@co<<de null}.</li>
     *     <li>{@code gameData} contains viewport dimensions.</li>
     * </ul>
     *
     * <p>Post-conditions:</p>
     * <ul>
     *     <li>The {@code entity} has been instantiated and added to the world.</li>
     * </ul>
     *
     * @param gameData GameData stores metadata about the game's state
     * @param world World stores the entities of the game.
     */
    void start(GameData gameData, World world);

    /**
     * <p>The method is called when the game stops.
     * Objects created by the plugins are responsible for deconstructing them as well.</p>
     *
     * <p>Pre-conditions:</p>
     * <ul>
     *     <li>{@code gameData} isn't {@code null}.</li>
     *     <li>{@code world} isn't {@code null}.</li>
     *     <li>{@code world} contains {@code Entities} that the plugin is responsible for.</li>
     * </ul>
     *
     * <p>Post-conditions:</p>
     * <ul>
     *     <li>The entity has been removed from the world.</li>
     * </ul>
     *
     * @param gameData GameData stores the metadata about the game’s state.
     * @param world World stores the entities of the game.
     */
    void stop(GameData gameData, World world);
}
