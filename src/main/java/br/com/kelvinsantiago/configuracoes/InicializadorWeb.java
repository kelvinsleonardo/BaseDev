package br.com.kelvinsantiago.configuracoes;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class InicializadorWeb implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.setConfigLocation("br.com.kelvinsantiago.configuracoes");

        Dynamic appServlet = servletContext.addServlet("appServlet", new DispatcherServlet(webApplicationContext));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");

        servletContext.addListener(new ContextLoaderListener(webApplicationContext));
        servletContext.addListener(new FiltroSessao());

        FilterRegistration.Dynamic filter = servletContext.addFilter("openEntityManagerFilter", buildOpenEntityManagerFilter());
        filter.addMappingForUrlPatterns(getDispatcherTypes(), true, "/*");

        //Filtro de codificação
        FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        fr.setInitParameter("encoding", "UTF-8");
        fr.setInitParameter("forceEncoding", "true");
        fr.addMappingForUrlPatterns(null, true, "/*");
    }

    private OpenEntityManagerInViewFilter buildOpenEntityManagerFilter() {
        OpenEntityManagerInViewFilter openEntityManagerInViewFilter = new OpenEntityManagerInViewFilter();
        openEntityManagerInViewFilter.setEntityManagerFactoryBeanName("entityManagerFactory");
        return openEntityManagerInViewFilter;
    }

    private EnumSet<DispatcherType> getDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC);
    }

}
