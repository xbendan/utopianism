package io.myosotisdev.utopianism.modules.dungeon;

import io.myosotisdev.utopianism.modules.region.Region;
import io.myosotisdev.utopianism.modules.player.ImplPlayer;
import net.minestom.server.instance.Instance;

import java.util.List;

/**
 *
 */
public class Dungeon
{
    private DungeonRecord record;
    private Region        newRegion;
    private Instance      world;

    private List<ImplPlayer> players;

    private volatile long countdown;
}
