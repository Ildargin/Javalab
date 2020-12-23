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
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
            configuration.setDefaultEncoding("UTF-8");
            try {
                configuration.setTemplateLoader(new FileTemplateLoader(new File("D:\\javaNEW\\src\\projects\\Annotations\\src\\main\\resources\\ftlh")));
                Template template = configuration.getTemplate("User.ftlh");
                List<Map<String, String>> inputs = new ArrayList<>();
                HtmlForm annotation = element.getAnnotation(HtmlForm.class);
                List<? extends Element> list = element.getEnclosedElements();
                for (Element value : list) {
                    HtmlInput htmlInput = value.getAnnotation(HtmlInput.class);
                    if (htmlInput != null) {
                        Map<String, String> map = new HashMap<>();
                        map.put("type", htmlInput.type());
                        map.put("name", htmlInput.name());
                        map.put("placeholder", htmlInput.placeholder());
                        inputs.add(map);
                    }
                }
                attributes.put("inputs", inputs);
                attributes.put("action", annotation.action());
                attributes.put("method", annotation.method());
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(out.toFile().getAbsolutePath()));
                    template.process(attributes, writer);
                } catch (IOException | TemplateException e) {
                    throw new IllegalStateException(e);
                }
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return true;
    }
}
