package example.test.application;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Driver application entry-point.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
            .headless(true)
            .logStartupInfo(false)
            .bannerMode(Banner.Mode.OFF)
            .web(WebApplicationType.NONE)
            .run(args);
    }
}
