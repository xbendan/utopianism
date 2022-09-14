package io.myosotisdev.utopianism.serialize;

import io.myosotisdev.utopianism.modules.effect.ParticleEffect;
import net.minestom.server.particle.Particle;
import org.jglrxavpok.hephaistos.nbt.NBTCompound;

import java.util.function.Function;

public class ParticleTagSerializer extends Serializer<ParticleEffect>
{
    @Override
    public String serialize(ParticleEffect obj)
    {
        return null;
    }

    @Override
    public ParticleEffect deserialize(String string)
    {
        String[] args = string.split(",");
        return null;
    }
}
