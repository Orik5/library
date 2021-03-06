package ua.lviv.dvoretskyi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories()
public class WebInit implements WebApplicationInitializer { //We use this class for

  public void onStartup(ServletContext servletContext)
      throws ServletException { //We make a (super)main servlet, something what can get all pages.

    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(WebConfig.class);

    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
    ServletRegistration.Dynamic registration = servletContext
        .addServlet("dispatcherServlet", dispatcherServlet);
    registration.setLoadOnStartup(1);
    registration.addMapping("/");
    registration.setMultipartConfig(new MultipartConfigElement("", 1000000, 1000000, 1000000));
  }


}
