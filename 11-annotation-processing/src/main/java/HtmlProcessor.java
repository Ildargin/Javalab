import com.google.auto.service.AutoService;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes(value = {"HtmlForm", "HtmlInput"})
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        for (Element element : annotatedElements) {
            String path = HtmlProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            path = path.substring(1) + element.getSimpleName().toString() + ".html";
            Path out = Paths.get(path);
            Map<String, Object> attributes = new HashMap<>();
            try {
                Configuration cfg = new Configuration(Configuration.VERSION_2_3_20);
                cfg.setDefaultEncoding("UTF-8");
                cfg.setTemplateLoader(new FileTemplateLoader(new File("C:\\Users\\Mi\\Desktop\\github-projects\\Javalab\\11-annotation-processing\\src\\main\\resources")));
                Template template = cfg.getTemplate("Form.ftl");
                HtmlForm formAnnotation = element.getAnnotation(HtmlForm.class);
                List<Input> inputsList = new ArrayList<>();
                for (Element el : element.getEnclosedElements()) {
                    HtmlInput input = el.getAnnotation(HtmlInput.class);
                    if (input != null) {
                        inputsList.add(Input.builder()
                                .placeholder(input.placeholder())
                                .name(input.name())
                                .type(input.type())
                                .build());
                    }
                }
                attributes.put("inputs", inputsList);
                attributes.put("action", formAnnotation.action());
                attributes.put("method", formAnnotation.method());

                BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile().getAbsolutePath()));
                template.process(attributes, writer);
            } catch (IOException | TemplateException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}



