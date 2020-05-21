package me.minjun.firebase.domain.health;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HealthLog {
    private String time;
    private double beat;
}
