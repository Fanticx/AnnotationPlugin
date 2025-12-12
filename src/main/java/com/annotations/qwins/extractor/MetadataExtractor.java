package com.annotations.qwins.extractor;

import com.annotations.qwins.annotations.core.*;
import com.annotations.qwins.annotations.core.ApiVersion.Target;
import com.annotations.qwins.annotations.core.repeatable.*;
import com.annotations.qwins.annotations.enums.LoadOrder;
import com.annotations.qwins.model.PluginMetadata;
import com.annotations.qwins.util.AnnotationPluginUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.TypeElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MetadataExtractor {
    private final TypeElement element;

    public MetadataExtractor(TypeElement element) {
        this.element = element;
    }

    public PluginMetadata extract() {
        return new PluginMetadata(
                getName(),
                getVersion(),
                getDescription(),
                getAuthors(),
                getWebsite(),
                getPrefix(),
                getApiVersion(),
                getLoadOrder(),
                getLibraries(),
                getDepend(),
                getSoftDepend(),
                getCommands(),
                getPermissions(),
                element.getQualifiedName().toString()
        );
    }

    @NotNull
    private String getName() {
        return element.getAnnotation(Name.class).value();
    }

    @NotNull
    private String getVersion() {
        return Optional.ofNullable(element.getAnnotation(Version.class))
                .map(Version::value)
                .orElse("1.0");
    }

    @Nullable
    private String getDescription() {
        return Optional.ofNullable(element.getAnnotation(Description.class))
                .map(Description::value)
                .orElse(null);
    }

    @Nullable
    private String getWebsite() {
        return Optional.ofNullable(element.getAnnotation(Website.class))
                .map(Website::value)
                .orElse(null);
    }

    @Nullable
    private String getPrefix() {
        return Optional.ofNullable(element.getAnnotation(Prefix.class))
                .map(Prefix::value)
                .orElse(null);
    }

    @NotNull
    private List<String> getAuthors() {
        return Optional.ofNullable(element.getAnnotation(Authors.class))
                .map(authors -> Arrays.asList(authors.value()))
                .orElse(Collections.emptyList());
    }

    @NotNull
    private Target getApiVersion() {
        return Optional.ofNullable(element.getAnnotation(com.annotations.qwins.annotations.core.ApiVersion.class))
                .map(com.annotations.qwins.annotations.core.ApiVersion::value)
                .orElse(Target.v1_21);
    }

    @NotNull
    private LoadOrder getLoadOrder() {
        return Optional.ofNullable(element.getAnnotation(Load.class))
                .map(Load::value)
                .orElse(LoadOrder.POSTWORLD);
    }

    @NotNull
    private List<String> getLibraries() {
        return AnnotationPluginUtils.getStringsFromRepeatableAnnotation(element, Library.class, Library::value);
    }

    @NotNull
    private List<String> getDepend() {
        return AnnotationPluginUtils.getStringsFromRepeatableAnnotation(element, Depend.class, Depend::value);
    }

    @NotNull
    private List<String> getSoftDepend() {
        return AnnotationPluginUtils.getStringsFromRepeatableAnnotation(element, SoftDepend.class, SoftDepend::value);
    }

    @NotNull
    private List<Command> getCommands() {
        return AnnotationPluginUtils.getValuesFromRepeatableAnnotation(element, Command.class, cmd -> cmd);
    }

    @NotNull
    private List<Permission> getPermissions() {
        return AnnotationPluginUtils.getValuesFromRepeatableAnnotation(element, Permission.class, perm -> perm);
    }
}