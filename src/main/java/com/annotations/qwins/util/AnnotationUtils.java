package com.annotations.qwins.util;

import javax.lang.model.element.Element;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class AnnotationUtils {

    private AnnotationUtils() {}

    public static <A extends Annotation, R> List<R> getValuesFromRepeatableAnnotation(Element element, Class<A> annotationClass, Function<A, R> mapper) {
        return Arrays.stream(element.getAnnotationsByType(annotationClass))
                .map(mapper)
                .collect(Collectors.toList());
    }

    public static <A extends Annotation> List<String> getStringsFromRepeatableAnnotation(Element element, Class<A> annotationClass, Function<A, String[]> mapper) {
        return Arrays.stream(element.getAnnotationsByType(annotationClass))
                .flatMap(a -> Arrays.stream(mapper.apply(a)))
                .collect(Collectors.toList());
    }
}