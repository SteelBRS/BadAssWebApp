package org.elite.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created with IntelliJ IDEA.
 * User: Torben Vesterager
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.elite.java")
public class AppConfig {
}
