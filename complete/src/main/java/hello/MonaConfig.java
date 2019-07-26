package hello;

import io.monalabs.client.MonaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonaConfig {

    @Bean(destroyMethod="close", name="monaClient")
    public MonaClient getMonaClient() {
        return new MonaClient.Builder(
                "gong.monalabs.io", 24224, "nemo_test99").build();
    }

}
