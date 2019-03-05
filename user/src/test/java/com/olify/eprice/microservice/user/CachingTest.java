package com.olify.eprice.microservice.user;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.olify.eprice.microservice.component.UserRegistrar;
import com.olify.eprice.microservice.model.OlifyUser;
import com.olify.eprice.microservice.repository.OlifyUserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureTestDatabase
//@AutoConfigureCache
public class CachingTest {
	@Autowired
	private UserRegistrar userRegistrar;
	
	@Autowired
	private OlifyUserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		userRegistrar = null;
		userRepository = null;
	}

	@Test
	public void testCaching() throws Exception{
		given(userRepository.findByName(anyString())).willReturn(
				new OlifyUser(1L, "moses", "emacsone@aol.com", "password", "0773405024", "male", "trader", new Date(1/12/2019) ));
		
		userRegistrar.getUserDetails("moses");
		
		verify(userRepository, times(1)).findByName("moses");
	}

}
