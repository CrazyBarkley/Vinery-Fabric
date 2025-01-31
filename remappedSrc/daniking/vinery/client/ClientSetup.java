package daniking.vinery.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import daniking.vinery.VineryIdentifier;
import daniking.vinery.block.entity.chair.ChairRenderer;
import daniking.vinery.client.gui.CookingPotGui;
import daniking.vinery.client.gui.FermentationBarrelGui;
import daniking.vinery.client.gui.StoveGui;
import daniking.vinery.client.gui.WinePressGui;
import daniking.vinery.client.render.block.StorageBlockEntityRenderer;
import daniking.vinery.client.model.MuleModel;
import daniking.vinery.client.render.entity.MuleRenderer;
import daniking.vinery.client.render.entity.WanderingWinemakerRenderer;
import daniking.vinery.util.networking.VineryMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GrassColor;

@Environment(EnvType.CLIENT)
public class ClientSetup implements ClientModInitializer {

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new VineryIdentifier("trader_mule"), "main");

    @Override
    public void onInitializeClient() {
        VineryMessages.registerS2CPackets();
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(Sheets.SIGN_SHEET, ObjectRegistry.CHERRY_SIGN.getTexture()));
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ObjectRegistry.RED_GRAPE_BUSH, ObjectRegistry.WHITE_GRAPE_BUSH,
                                                ObjectRegistry.CHERRY_DOOR, ObjectRegistry.COOKING_POT,
                                                ObjectRegistry.CHERRY_JAM, ObjectRegistry.CHERRY_JAR, ObjectRegistry.FERMENTATION_BARREL,
                                                ObjectRegistry.MELLOHI_WINE, ObjectRegistry.CLARK_WINE, ObjectRegistry.BOLVAR_WINE, ObjectRegistry.CHERRY_WINE,
                                                ObjectRegistry.KING_DANIS_WINE, ObjectRegistry.CHERRY_JAR, ObjectRegistry.CHENET_WINE, ObjectRegistry.MELLOHI_WINE,
                                                ObjectRegistry.NOIR_WINE, ObjectRegistry.WINE_BOTTLE, ObjectRegistry.TABLE, ObjectRegistry.APPLE_CIDER,
                                                ObjectRegistry.APPLE_JAM, ObjectRegistry.APPLE_JUICE, ObjectRegistry.APPLE_WINE, ObjectRegistry.SOLARIS_WINE, ObjectRegistry.JELLIE_WINE,
                                                ObjectRegistry.AEGIS_WINE, ObjectRegistry.SWEETBERRY_JAM, ObjectRegistry.GRAPE_JAM, ObjectRegistry.KELP_CIDER


        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), ObjectRegistry.GRAPEVINE_STEM, ObjectRegistry.WINE_BOTTLE,
                                               ObjectRegistry.RED_GRAPEJUICE_WINE_BOTTLE, ObjectRegistry.WHITE_GRAPEJUICE_WINE_BOTTLE,
                                               ObjectRegistry.WINE_BOX, ObjectRegistry.FLOWER_BOX_ALLIUM, ObjectRegistry.FLOWER_BOX_AZURE_BLUET,
                                               ObjectRegistry.FLOWER_BOX, ObjectRegistry.FLOWER_BOX_BLUE_ORCHID, ObjectRegistry.FLOWER_BOX_BLUE_DANDELION,
                                               ObjectRegistry.FLOWER_BOX_BLUE_CORNFLOWER, ObjectRegistry.FLOWER_BOX_BLUE_LILY_OF_THE_VALLEY,
                                               ObjectRegistry.FLOWER_BOX_BLUE_ORANGE_TULIP, ObjectRegistry.FLOWER_BOX_BLUE_OXEYE_DAISY,
                                               ObjectRegistry.FLOWER_BOX_BLUE_PINK_TULIP, ObjectRegistry.FLOWER_BOX_BLUE_POPPY,
                                               ObjectRegistry.FLOWER_BOX_BLUE_RED_TULIP, ObjectRegistry.FLOWER_BOX_BLUE_WHITE_TULIP,
                                               ObjectRegistry.FLOWER_BOX_BLUE_WHITER_ROSE, ObjectRegistry.FLOWER_POT,
                                               ObjectRegistry.CHAIR,
                                               ObjectRegistry.WINE_PRESS, ObjectRegistry.GRASS_SLAB, ObjectRegistry.CHERRY_JAR,
                                               ObjectRegistry.CHERRY_SAPLING, ObjectRegistry.OLD_CHERRY_SAPLING, ObjectRegistry.KITCHEN_SINK, ObjectRegistry.STACKABLE_LOG
                                              );

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(), ObjectRegistry.WINDOW);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> BiomeColors.getAverageGrassColor(world, pos), ObjectRegistry.GRASS_SLAB);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColor.get(1.0, 0.5), ObjectRegistry.GRASS_SLAB);
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world == null || pos == null) {
                return -1;
            }
            return BiomeColors.getAverageWaterColor(world, pos);
            }, ObjectRegistry.KITCHEN_SINK);
        MenuScreens.register(VineryScreenHandlerTypes.STOVE_GUI_HANDLER, StoveGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.FERMENTATION_BARREL_GUI_HANDLER, FermentationBarrelGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.COOKING_POT_SCREEN_HANDLER, CookingPotGui::new);
        MenuScreens.register(VineryScreenHandlerTypes.WINE_PRESS_SCREEN_HANDLER, WinePressGui::new);
        TerraformBoatClientHelper.registerModelLayers(new VineryIdentifier("cherry"));


        EntityRendererRegistry.register(VineryEntites.MULE, MuleRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(LAYER_LOCATION, MuleModel::getTexturedModelData);

        EntityRendererRegistry.register(VineryEntites.WANDERING_WINEMAKER, WanderingWinemakerRenderer::new);
        EntityRendererRegistry.register(VineryBlockEntityTypes.CHAIR, ChairRenderer::new);

        CustomArmorRegistry.registerModels();

        BlockEntityRendererRegistry.register(VineryBlockEntityTypes.STORAGE_ENTITY, StorageBlockEntityRenderer::new);
    }
    
    public static Player getClientPlayer() {
        return Minecraft.getInstance().player;
    }
    
}