package com.github.enbyex.nbx.mod.studioimpl;

import com.github.enbyex.nbx.studio.api.ISound;
import com.github.enbyex.nbx.studio.api.ISoundAdapter;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import net.minecraft.client.Minecraft;

/**
 * @author soniex2
 */
public class MCSoundAdapter implements ISoundAdapter {

    private Minecraft mc;
    private LoadingCache<String, MCSound> pool;

    public MCSoundAdapter(Minecraft mc) {
        this.mc = mc;
        this.pool = CacheBuilder.newBuilder().softValues().build(new CacheLoader<String, MCSound>() {
            @Override
            public MCSound load(String key) {
                return new MCSound(MCSoundAdapter.this.mc, key);
            }
        });
    }

    /**
     * Get the sound name equivalent to Minecraft's "minecraft:note.bd".
     *
     * @return A sound name that can be passed to {@link #getSound(String name)} to retrieve the sound.
     */
    @Override
    public String getBassDrumSoundName() {
        return "minecraft:note.bd";
    }

    /**
     * Get the sound name equivalent to Minecraft's "minecraft:note.bassattack".
     *
     * @return A sound name that can be passed to {@link #getSound(String name)} to retrieve the sound.
     */
    @Override
    public String getBassGuitarSoundName() {
        return "minecraft:note.bassattack";
    }

    /**
     * Get the sound name equivalent to Minecraft's "minecraft:note.hat".
     *
     * @return A sound name that can be passed to {@link #getSound(String name)} to retrieve the sound.
     */
    @Override
    public String getClicksSoundName() {
        return "minecraft:note.hat";
    }

    /**
     * Get the sound name equivalent to Minecraft's "minecraft:note.harp".
     *
     * @return A sound name that can be passed to {@link #getSound(String name)} to retrieve the sound.
     */
    @Override
    public String getPianoSoundName() {
        return "minecraft:note.harp";
    }

    /**
     * Get the sound name equivalent to Minecraft's "minecraft:note.snare".
     *
     * @return A sound name that can be passed to {@link #getSound(String name)} to retrieve the sound.
     */
    @Override
    public String getSnareSoundName() {
        return "minecraft:note.snare";
    }

    /**
     * Get the {@link com.github.enbyex.nbx.studio.api.ISound} that corresponds to the given name.
     *
     * @param name The name used to lookup the {@link com.github.enbyex.nbx.studio.api.ISound}.
     * @return An {@link com.github.enbyex.nbx.studio.api.ISound} that corresponds to the given name.
     */
    @Override
    public ISound getSound(String name) {
        return pool.getUnchecked(name);
    }
}
