package me.minjun.firebase.domain.health;

import lombok.Getter;

@Getter
public class HealthInfoEvent {
    private String message;
    private String email;

    public HealthInfoEvent(String message, String email) {
        this.message = message;
        this.email = email;
    }
}
