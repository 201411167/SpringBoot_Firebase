package me.minjun.firebase.domain.health;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HealthInfoEventHandler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @EventListener
    @Async
    public void handle(HealthInfoEvent event){
        logger.info("Health Info changed : " + event.getEmail() + ", " + event.getMessage());
        //todo
        // when there's changed data about health logs,
        // deal with those logs using appropriate algorithm
    }
}
