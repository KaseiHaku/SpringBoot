package kasei.springboot.app.listener;

import org.springframework.boot.context.event.*;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class CustomApplicationListener implements ApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("ApplicationEvent ==== " + event.getClass().getTypeName());



    }
}
