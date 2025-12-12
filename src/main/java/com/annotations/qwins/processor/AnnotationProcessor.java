package com.annotations.qwins.processor;

import com.annotations.qwins.annotations.core.Name;
import com.annotations.qwins.builder.PluginYmlBuilder;
import com.annotations.qwins.extractor.MetadataExtractor;
import com.annotations.qwins.model.PluginMetadata;
import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@AutoService(Processor.class)
@SupportedAnnotationTypes("com.annotations.qwins.annotations.core.Name")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class AnnotationProcessor extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.filer = processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Name.class)) {
            if (element instanceof TypeElement typeElement) {
                try {
                    PluginMetadata metadata = new MetadataExtractor(typeElement).extract();
                    String yaml = new PluginYmlBuilder(metadata).build();

                    writePluginYml(yaml, typeElement);
                } catch (Exception ignored) {
                }
            }
        }
        return true;
    }

    private void writePluginYml(String content, TypeElement element) throws IOException {
        FileObject file = filer.createResource(StandardLocation.CLASS_OUTPUT, "", "plugin.yml", element);
        try (Writer writer = file.openWriter()) {
            writer.write(content);
        }
    }
}