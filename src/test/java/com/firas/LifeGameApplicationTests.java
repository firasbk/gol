package com.firas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.life.lifegame.LifeGameApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LifeGameApplication.class)
@WebAppConfiguration
public class LifeGameApplicationTests {

	@Test
	public void contextLoads() {
	}

}
