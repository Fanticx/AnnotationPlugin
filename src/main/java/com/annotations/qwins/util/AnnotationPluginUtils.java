package com.annotations.qwins.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AnnotationPluginUtils {

    private AnnotationPluginUtils() {
        throw new UnsupportedOperationException("Утилитный класс");
    }

    public static boolean isBlank(@Nullable String str) {
        return str == null || str.trim().isEmpty();
    }

    @NotNull
    public static <A extends Annotation, R> List<R> getValuesFromRepeatableAnnotation(
            Element element, Class<A> annotationClass, Function<A, R> mapper) {
        return Arrays.stream(element.getAnnotationsByType(annotationClass))
                .map(mapper)
                .collect(Collectors.toList());
    }

    @NotNull
    public static <A extends Annotation> List<String> getStringsFromRepeatableAnnotation(
            Element element, Class<A> annotationClass, Function<A, String[]> mapper) {
        return Arrays.stream(element.getAnnotationsByType(annotationClass))
                .flatMap(a -> Arrays.stream(mapper.apply(a)))
                .collect(Collectors.toList());
    }
}
