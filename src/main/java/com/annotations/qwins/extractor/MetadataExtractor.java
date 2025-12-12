package com.annotations.qwins.extractor;

import com.annotations.qwins.annotations.core.*;
import com.annotations.qwins.annotations.core.ApiVersion.Target;
import com.annotations.qwins.annotations.core.repeatable.*;
import com.annotations.qwins.annotations.enums.LoadOrder;
import com.annotations.qwins.model.PluginMetadata;
import com.annotations.qwins.util.AnnotationUtils;

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

    private String getName() {
        return element.getAnnotation(Name.class).value();
    }

    private String getVersion() {
        return Optional.ofNullable(element.getAnnotation(Version.class))
                .map(Version::value)
                .orElse("1.0");
    }

    private String getDescription() {
        return Optional.ofNullable(element.getAnnotation(Description.class))
                .map(Description::value)
                .orElse("");
    }

    private String getWebsite() {
        return Optional.ofNullable(element.getAnnotation(Website.class))
                .map(Website::value)
                .orElse("");
    }

    private String getPrefix() {
        return Optional.ofNullable(element.getAnnotation(Prefix.class))
                .map(Prefix::value)
                .orElse("");
    }

    private List<String> getAuthors() {
        return Optional.ofNullable(element.getAnnotation(Authors.class))
                .map(authors -> Arrays.asList(authors.value()))
                .orElse(Collections.emptyList());
    }

    private Target getApiVersion() {
        return Optional.ofNullable(element.getAnnotation(com.annotations.qwins.annotations.core.ApiVersion.class))
                .map(com.annotations.qwins.annotations.core.ApiVersion::value)
                .orElse(Target.v1_21);
    }

    private LoadOrder getLoadOrder() {
        return Optional.ofNullable(element.getAnnotation(Load.class))
                .map(Load::value)
                .orElse(LoadOrder.POSTWORLD);
    }

    private List<String> getLibraries() {
        return AnnotationUtils.getStringsFromRepeatableAnnotation(element, Library.class, Library::value);
    }

    private List<String> getDepend() {
        return AnnotationUtils.getStringsFromRepeatableAnnotation(element, Depend.class, Depend::value);
    }

    private List<String> getSoftDepend() {
        return AnnotationUtils.getStringsFromRepeatableAnnotation(element, SoftDepend.class, SoftDepend::value);
    }

    private List<Command> getCommands() {
        return AnnotationUtils.getValuesFromRepeatableAnnotation(element, Command.class, cmd -> cmd);
    }

    private List<Permission> getPermissions() {
        return AnnotationUtils.getValuesFromRepeatableAnnotation(element, Permission.class, perm -> perm);
    }
}