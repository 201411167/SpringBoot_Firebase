package me.minjun.firebase.service;

import me.minjun.firebase.domain.health.HealthInfo;
import me.minjun.firebase.domain.health.HealthInfoEvent;
import me.minjun.firebase.domain.health.HealthLog;
import me.minjun.firebase.domain.health.HealthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HealthService {

    @Autowired
    private HealthRepository healthRepo;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    public void insertHealthInfo(String email){
        Optional<HealthInfo> optionalHealthInfo = healthRepo.findById(email);
        if(optionalHealthInfo.isEmpty()){
            healthRepo.insert(HealthInfo.builder().email(email).build());
            eventPublisher.publishEvent(new HealthInfoEvent("Creating new healthInfo", email));
        }
    }

    public void updateHealthInfo(String email, HealthLog log){
        Optional<HealthInfo> optionalHealthInfo = healthRepo.findById(email);
        if(optionalHealthInfo.isPresent()){
            HealthInfo info = optionalHealthInfo.get();
            info.getLogs().add(log);
            healthRepo.save(info);
            eventPublisher.publishEvent(new HealthInfoEvent("Updating healthInfo", email));
        }
    }

    public List<HealthLog> getHealthLogs(String email){
        Optional<HealthInfo> optionalHealthInfo = healthRepo.findById(email);
        return optionalHealthInfo.map(HealthInfo::getLogs).orElse(null);
    }

    public void deleteHealthInfo(String email){
        Optional<HealthInfo> optionalHealthInfo = healthRepo.findById(email);
        optionalHealthInfo.ifPresent(healthInfo -> healthRepo.delete(healthInfo));
        eventPublisher.publishEvent(new HealthInfoEvent("Deleting healthInfo", email));
    }
}
