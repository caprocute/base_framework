package com.viettel.solution.base;

import com.viettel.solution.base.BaseFrameWorkApp;
import com.viettel.solution.base.config.AsyncSyncConfiguration;
import com.viettel.solution.base.config.EmbeddedRedis;
import com.viettel.solution.base.config.EmbeddedSQL;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 * Base composite annotation for integration tests.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = { BaseFrameWorkApp.class, AsyncSyncConfiguration.class })
@EmbeddedRedis
@EmbeddedSQL
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface IntegrationTest {
}
