package cc.dreamcode.template;

import cc.dreamcode.command.bukkit.BukkitCommandProvider;
import cc.dreamcode.menu.bukkit.BukkitMenuProvider;
import cc.dreamcode.menu.serializer.MenuBuilderSerializer;
import cc.dreamcode.notice.serializer.BukkitNoticeSerializer;
import cc.dreamcode.platform.DreamVersion;
import cc.dreamcode.platform.bukkit.DreamBukkitConfig;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.ConfigurationResolver;
import cc.dreamcode.platform.component.ComponentService;
import cc.dreamcode.platform.other.component.DreamCommandExtension;
import cc.dreamcode.template.command.ExampleCommand;
import cc.dreamcode.template.command.handler.InvalidInputHandlerImpl;
import cc.dreamcode.template.command.handler.InvalidPermissionHandlerImpl;
import cc.dreamcode.template.command.handler.InvalidSenderHandlerImpl;
import cc.dreamcode.template.command.handler.InvalidUsageHandlerImpl;
import cc.dreamcode.template.command.result.BukkitNoticeResolver;
import cc.dreamcode.template.config.MessageConfig;
import cc.dreamcode.template.config.PluginConfig;
import cc.dreamcode.utilities.adventure.AdventureProcessor;
import cc.dreamcode.utilities.adventure.AdventureUtil;
import cc.dreamcode.utilities.bukkit.StringColorUtil;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.tasker.bukkit.BukkitTasker;
import lombok.Getter;
import lombok.NonNull;

public final class TemplatePlugin extends DreamBukkitPlatform implements DreamBukkitConfig {

    @Getter private static TemplatePlugin instance;

    @Override
    public void load(@NonNull ComponentService componentService) {
        instance = this;

        AdventureUtil.setRgbSupport(true);
        StringColorUtil.setColorProcessor(new AdventureProcessor());
    }

    @Override
    public void enable(@NonNull ComponentService componentService) {
        componentService.setDebug(false);

        this.registerInjectable(BukkitTasker.newPool(this));
        this.registerInjectable(BukkitMenuProvider.create(this));

        this.registerInjectable(BukkitCommandProvider.create(this));
        componentService.registerExtension(DreamCommandExtension.class);

        componentService.registerResolver(ConfigurationResolver.class);
        componentService.registerComponent(MessageConfig.class);

        componentService.registerComponent(BukkitNoticeResolver.class);
        componentService.registerComponent(InvalidInputHandlerImpl.class);
        componentService.registerComponent(InvalidPermissionHandlerImpl.class);
        componentService.registerComponent(InvalidSenderHandlerImpl.class);
        componentService.registerComponent(InvalidUsageHandlerImpl.class);

        componentService.registerComponent(PluginConfig.class);

        componentService.registerComponent(ExampleCommand.class);
    }

    @Override
    public void disable() {
        // features need to be call when server is stopping
    }

    @Override
    public @NonNull DreamVersion getDreamVersion() {
        return DreamVersion.create("Dream-Template", "1.0-InDEV", "author");
    }

    @Override
    public @NonNull OkaeriSerdesPack getConfigSerdesPack() {
        return registry -> {
            registry.register(new BukkitNoticeSerializer());
            registry.register(new MenuBuilderSerializer());
        };
    }

}
