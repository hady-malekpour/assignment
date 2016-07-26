package com.edia;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AssignmentApplication.class)
@WebAppConfiguration
public class AssignmentApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void contextLoads() {
		Assert.assertTrue(applicationContext.containsBean("mappingJackson2HttpMessageConverter"));
		Assert.assertTrue(applicationContext.containsBean("indexJmsContainerFactory"));
		Assert.assertTrue(applicationContext.containsBean("myJmsTemplate"));
	}

}
