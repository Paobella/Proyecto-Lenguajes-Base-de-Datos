package com.PlushiePro.PlushiePro;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {
@Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.getDefault());
        slr.setLocaleAttributeName("session.current.locale");
        slr.setTimeZoneAttributeName("session.current.timezone");
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /* Los siguiente métodos son para implementar el tema de seguridad dentro del proyecto */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/registro/nuevo").setViewName("/registro/nuevo");
    }

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                .requestMatchers("/", "/index", "/errores/**",
                        "/carrito/**", "/pruebas/**", "/reportes/**",
                        "/registro/**", "/js/**","/css/**","/images/**", "/webjars/**","/producto/listado/**")
                .permitAll()
                .requestMatchers(
                         "/factura/listado","/venta/listado","/productos/query1", "/facturar/carrito","/producto/nuevo", "/producto/guardar", "/producto/listado/1",
                        "/producto/listado/2","/producto/listado/3","/producto/listado/4",
                        "/producto/listado/5", "/producto/listado/6","/producto/listado/7",
                        "/producto/listado/8","/producto/listado/9", "/producto/listado/10",
                        "/producto/listado/11", "/producto/listado/12", "/producto/listado/13",
                        "/producto/listado/14", "/producto/listado/15",
                        "/producto/modificar/**","/producto/categoria", "/producto/listado","/producto/eliminar/**",
                        "/usuario/nuevo", "/usuario/guardar","/usuario/listado",
                        "/usuario/modificar/**","/css/**","/images/**", "/usuario/eliminar/**",
                        "/reportes/**"
                ).hasAnyRole("ADMIN", "USER")
                .requestMatchers(
                        "/productos/query1","/producto/listado",
                        "/producto/listado/1","/producto/listado/2",
                        "/producto/listado/3","/producto/listado/4",
                        "/producto/listado/5","/producto/listado/6",
                        "/producto/listado/7","/producto/listado/8",
                        "/producto/listado/9","/producto/listado/10",
                        "/producto/listado/11","/producto/listado/12",
                        "/producto/listado/13", "/producto/listado/14",
                        "/producto/listado/15","/usuario/listado",
                        "/carrito/listado", "/productos/categoria",
                        "/facturar/carrito"
                ).hasRole("ADMIN")
                .requestMatchers("/venta/listado")
                .hasRole("USER")
                 .requestMatchers("/factura/listado")
                )
                .formLogin((form) -> form
                .loginPage("/login").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

    }
}