package me.minjun.firebase.service;

import lombok.extern.slf4j.Slf4j;
import me.minjun.firebase.domain.health.HealthInfo;
import me.minjun.firebase.domain.health.HealthLog;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HealthServiceTest {

    @Autowired
    HealthService service;

    @Before
    public void init(){
        service.deleteHealthInfo("test_email");
    }

    @After
    public void cleanUp(){
        service.deleteHealthInfo("test_email");
    }

    @Test
    public void 헬스_서비스_테스트(){
        service.insertHealthInfo("test_email");
        service.updateHealthInfo("test_email", new HealthLog("test_time_0", 59.0));
        List<HealthLog> list_0 = service.getHealthLogs("test_email");
        assertThat(list_0.size(), is(1));
        assertThat(list_0.get(0).getTime(), is("test_time_0"));
        assertThat(list_0.get(0).getBeat(), is(59.0));

        service.updateHealthInfo("test_email", new HealthLog("test_time_1", 60.0));;
        List<HealthLog> list_1 = service.getHealthLogs("test_email");
        assertThat(list_1.size(), is(2));
        assertThat(list_1.get(0).getTime(), is("test_time_0"));
        assertThat(list_1.get(0).getBeat(), is(59.0));
        assertThat(list_1.get(1).getTime(), is("test_time_1"));
        assertThat(list_1.get(1).getBeat(), is(60.0));
    }
}