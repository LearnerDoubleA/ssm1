package szh.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.*;


@Configuration
@EnableScheduling
public class ThreadSpringConfig {

    private Properties prop = new Properties();

    public ThreadSpringConfig() {
        InputStream in = ThreadSpringConfig.class.getClassLoader().getResourceAsStream("main/resources/conf/threadPool.properties");

        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        //int maxPoolSize = Runtime.getRuntime().availableProcessors();

        String poolSize = prop.getProperty("thread.poolSize");
        String aliveSeconds = prop.getProperty("thread.aliveSeconds");
        String queueSize = prop.getProperty("thread.queue");
        String maxSize = prop.getProperty("thread.maxPoolSize");

        int coreSize = Integer.valueOf(poolSize);
        int maxPoolSize = Integer.valueOf(maxSize);
        int aliveSeconds2 = Integer.valueOf(aliveSeconds);

        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(Integer.valueOf(queueSize));

        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize,maxPoolSize,aliveSeconds2, TimeUnit.MILLISECONDS,queue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }

}
