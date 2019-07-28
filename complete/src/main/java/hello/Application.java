package hello;

import io.monalabs.client.MonaClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        //SpringApplication.run(Application.class, args);
        System.out.println("Spring Boot application started");
        ConfigurableApplicationContext ctx = new
                SpringApplicationBuilder(Application.class)
                .child(MonaConfig.class)
                .web(WebApplicationType.NONE)
                .run();

        //ApplicationContext context = new AnnotationConfigApplicationContext(MonaConfig.class);
        MonaClient monaClient = ctx.getBean(MonaClient.class);
        String msg = "{'experiment': 'f3', 'name': 'y1'}";
        String arcClass = "ARC_CLASS_99";
        monaClient.exportStandalone(arcClass, msg);
        //System.out.println(String.format("sent %s to mona with arc class %s with bean!", msg, arcClass));

        //ctx.getBean(TerminateBean.class);
        //ctx.close();
        System.exit(SpringApplication.exit(ctx, () -> 0));
    }
}
