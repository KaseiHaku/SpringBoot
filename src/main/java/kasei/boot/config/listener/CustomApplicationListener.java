package kasei.boot.config.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;


public class CustomApplicationListener implements ApplicationListener {


    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("ApplicationEvent ==== " + event.getClass().getTypeName());



    }
}
