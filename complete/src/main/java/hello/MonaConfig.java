package hello;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import io.monalabs.client.MonaClient;
import org.komamitsu.fluency.fluentd.ingester.sender.NetworkSender;
import org.komamitsu.fluency.fluentd.ingester.sender.RetryableSender;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonaConfig {

    @Bean(destroyMethod="close", name="monaClient")
    public MonaClient getMonaClient() {
        //ContextSelector selector = ContextSelectorStaticBinder.getSingleton().getContextSelector();
        //LoggerContext loggerContext = selector.getLoggerContext();
        //loggerContext.
        String host = "going.monalabs.io";
        Logger networkSender = (Logger) LoggerFactory.getLogger(NetworkSender.class);
        Logger retryableSender = (Logger) LoggerFactory.getLogger(RetryableSender.class);
        networkSender.setLevel(Level.OFF);
        retryableSender.setLevel(Level.OFF);
        int port = 24224;
        System.out.println(String.format("Returning mona client with address %s:%d", host, port));
        return new MonaClient.Builder(
                host, port, "nemo_test99").build();
    }

}
