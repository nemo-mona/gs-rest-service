package hello;

import java.util.concurrent.atomic.AtomicLong;

import io.monalabs.client.MonaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("greeting started");
        /*String host = "going.monalabs.io";
        int port = 24224;
        String monaUserId = "nemo_test99";*/
        ApplicationContext context = new AnnotationConfigApplicationContext(MonaConfig.class);
        MonaClient monaClient = context.getBean("monaClient", MonaClient.class);
        String msg = String.format("{'experiment': 'f1', 'name': '%s'}", name);
        String arcClass = "ARC_CLASS_99";
        //try (MonaClient monaClient = new MonaClient.Builder(host, port, monaUserId).build()) {
        monaClient.exportStandalone(arcClass, msg);
        //}
        System.out.println(String.format("sent %s to mona with arc class %s with bean!", msg, arcClass));
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
