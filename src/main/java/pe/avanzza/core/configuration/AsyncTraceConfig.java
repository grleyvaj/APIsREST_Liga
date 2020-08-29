package pe.avanzza.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author Gloria R. Leyva Jerez
 * Configure a different THREAD to persist traces
 */
@EnableAsync
@Configuration
public class AsyncTraceConfig {

    /**
     * Create a thread pool with a size between 2 and 10, and a request queue of size 20
     *
     * @return Executor The extensible thread pool implementation
     */
    @Bean
    public Executor taskExecutorTrace() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(20);
        executor.initialize();
        return executor;
    }
}
