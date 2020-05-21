package me.minjun.firebase.domain.health;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class HealthInfo {
    @Id
    private String email;
    private List<HealthLog> logs;

    @Builder
    public HealthInfo(String email) {
        this.email = email;
        this.logs = new ArrayList<>();
    }
}
