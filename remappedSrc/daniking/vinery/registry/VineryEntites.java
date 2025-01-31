package daniking.vinery.registry;

import daniking.vinery.VineryIdentifier;
import daniking.vinery.entity.TraderMuleEntity;
import daniking.vinery.entity.WanderingWinemakerEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.horse.Llama;

public class VineryEntites {
	
	public static final EntityType<TraderMuleEntity> MULE = Registry.register(Registry.ENTITY_TYPE,
			new VineryIdentifier("mule"),
			FabricEntityTypeBuilder.create(MobCategory.CREATURE, TraderMuleEntity::new).dimensions(new EntityDimensions(0.9f, 1.87f, true)).trackRangeChunks(10).build()
	                                                                         );
	
	public static final EntityType<WanderingWinemakerEntity> WANDERING_WINEMAKER = Registry.register(Registry.ENTITY_TYPE,
			new VineryIdentifier("wandering_winemaker"),
			FabricEntityTypeBuilder.create(MobCategory.CREATURE, WanderingWinemakerEntity::new)
			                       .dimensions(new EntityDimensions(0.6f, 1.95f, true))
			                       .trackRangeChunks(10)
			                       .build()
	                                                                                                );
	
	public static void init() {
		FabricDefaultAttributeRegistry.register(MULE, Llama.createAttributes().add(Attributes.MOVEMENT_SPEED, 0.2f));
		FabricDefaultAttributeRegistry.register(WANDERING_WINEMAKER, WanderingWinemakerEntity.createMobAttributes());
	}
}