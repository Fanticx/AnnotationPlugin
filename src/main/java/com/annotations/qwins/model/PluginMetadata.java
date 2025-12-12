package com.annotations.qwins.model;

import com.annotations.qwins.annotations.core.ApiVersion.Target;
import com.annotations.qwins.annotations.core.repeatable.Command;
import com.annotations.qwins.annotations.core.repeatable.Permission;
import com.annotations.qwins.annotations.enums.LoadOrder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class PluginMetadata {
    private final String name;
    private final String version;
    private final String description;
    private final List<String> authors;
    private final String website;
    private final String prefix;
    private final Target apiVersion;
    private final LoadOrder loadOrder;
    private final List<String> libraries;
    private final List<String> depend;
    private final List<String> softDepend;
    private final List<Command> commands;
    private final List<Permission> permissions;
    private final String mainClass;

    public PluginMetadata(
            String name, String version, String description, List<String> authors,
            String website, String prefix, Target apiVersion, LoadOrder loadOrder,
            List<String> libraries, List<String> depend, List<String> softDepend,
            List<Command> commands, List<Permission> permissions, String mainClass
    ) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.authors = authors;
        this.website = website;
        this.prefix = prefix;
        this.apiVersion = apiVersion;
        this.loadOrder = loadOrder;
        this.libraries = libraries;
        this.depend = depend;
        this.softDepend = softDepend;
        this.commands = commands;
        this.permissions = permissions;
        this.mainClass = mainClass;
    }

    @NotNull public String name() { return name; }
    @NotNull public String version() { return version; }
    @Nullable public String description() { return description; }
    @NotNull public List<String> authors() { return authors; }
    @Nullable public String website() { return website; }
    @Nullable public String prefix() { return prefix; }
    @NotNull public Target apiVersion() { return apiVersion; }
    @NotNull public LoadOrder loadOrder() { return loadOrder; }
    @NotNull public List<String> libraries() { return libraries; }
    @NotNull public List<String> depend() { return depend; }
    @NotNull public List<String> softDepend() { return softDepend; }
    @NotNull public List<Command> commands() { return commands; }
    @NotNull public List<Permission> permissions() { return permissions; }
    @NotNull public String mainClass() { return mainClass; }
}