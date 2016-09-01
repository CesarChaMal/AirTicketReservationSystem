package com.crossover.techtrial;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crossover.techtrial.Application;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
//@SpringApplicationConfiguration(classes = Application.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//@ContextConfiguration(locations = {"file:src/test/**/applicationContext-test.xml"})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerServiceAgentSvcTest extends ParentTestCase {

	private static final Logger log = Logger.getLogger(CustomerServiceAgentSvcTest.class);
    
	@Rule
	public MyJUnitStopWatch stopwatch = new MyJUnitStopWatch();

//	@Resource
//	private FilterChainProxy springSecurityFilterChain;

	@Resource
	private WebApplicationContext webApplicationContext;

	private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

	private MockMvc mockMvc;

	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDummy() {
		Assert.assertEquals(true, true);
	}
	
	public class JSON_STUBS {}
	public class JsonSalvo extends JSON_STUBS {public String[] salvo;};
	public class JsonSalvoShot extends JSON_STUBS {public String salvo; public String shots;};
	
}
