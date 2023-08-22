package be.fgov.sfpd.file;

import javax.servlet.MultipartConfigElement;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ServletRegistrationBean fileUploadRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/upload/*");
        servlet.setName("FileUploadServlet");
        servlet.setMultipartConfig(new MultipartConfigElement("/tmp", 1024*1024, 1024*1024+1024, 1024*1024));

        return servlet;
    }
}
