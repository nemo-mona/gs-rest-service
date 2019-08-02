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

        System.out.println("Spring Boot application started");

        ApplicationContext context = new AnnotationConfigApplicationContext(MonaConfig.class);
        MonaClient monaClient = context.getBean(MonaClient.class);
        for (int i = 0; i < 3; i++) {
            String msg = String.format("{'selected-language':'eng','num-of-segments':14," +
                    "'languages':[{'language':'eng','score':0.8718847080597933}," +
                    "{'language':'spa','score':0.08606883666153584}," +
                    "{'language':'fre','score':0.011074202120383153}," +
                    "{'language':'ger','score':0.006276969792140029}," +
                    "{'language':'dan','score':0.005572437900292508}," +
                    "{'language':'heb','score':0.005427145500735063}," +
                    "{'language':'rus','score':0.003932594815849529}," +
                    "{'language':'por','score':0.0036261475189298185}," +
                    "{'language':'jpn','score':0.002576497065849535}," +
                    "{'language':'swe','score':0.002090600870917034}," +
                    "{'language':'ita','score':0.0014699192180312732}]," +
                    "'call-id':2576211608781881271,'iteration':%d}", i);
            String msg2 = String.format("{'input_output_disagreement_percentage':0.3666921313979841," +
                    "'input_output_disagreement_duration':0.23999999999998067," +
                    "'mean_confidence':0.9980756680070021," +
                    "'confidence_percentile_5':0.9999999968637782," +
                    "'confidence_percentile_10':0.9999999999879733," +
                    "'call-id':7736384366709880150,'iteration':%d};", i);
            String arcClass = "ARC_CLASS_101";
            int sleepTimeSec = (int)(Math.random() * 5);
            System.out.println(String.format("good night, see you in %d seconds", sleepTimeSec));
            monaClient.exportStandalone(arcClass, msg);
            System.out.println(String.format("sent %s to mona with arc class %s", msg, arcClass));
            try {
                Thread.sleep(90000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted, going on");
                throw new RuntimeException("interrupted");
            }
            System.out.println("morning");
            monaClient.exportStandalone(arcClass, msg2);
            System.out.println(String.format("sent %s to mona with arc class %s", msg2, arcClass));
        }
        //ctx.getBean(TerminateBean.class);
        //context.close();
        System.exit(SpringApplication.exit(context, () -> 0));
    }
}
