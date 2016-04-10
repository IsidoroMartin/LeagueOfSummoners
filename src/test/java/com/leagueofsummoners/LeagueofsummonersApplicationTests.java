package com.leagueofsummoners;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.leagueofsummoners.LeagueofsummonersApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LeagueofsummonersApplication.class)
@WebAppConfiguration
public class LeagueofsummonersApplicationTests {

	@Test
	public void contextLoads() {
	}

}
