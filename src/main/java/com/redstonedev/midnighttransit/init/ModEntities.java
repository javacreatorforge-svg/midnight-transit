package com.redstonedev.midnighttransit.init;

import com.redstonedev.midnighttransit.MidnightTransit;
import com.redstonedev.midnighttransit.entity.ConductorNo9Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MidnightTransit.MODID);

    public static final RegistryObject<EntityType<ConductorNo9Entity>> CONDUCTOR_NO_9 =
            ENTITIES.register("conductor_no_9", () -> EntityType.Builder
                    .<ConductorNo9Entity>of(ConductorNo9Entity::new, MobCategory.MONSTER)
                    .sized(1.0F, 3.4F) // tall and intimidating
                    .clientTrackingRange(12)
                    .build(new ResourceLocation(MidnightTransit.MODID, "conductor_no_9").toString()));
}
