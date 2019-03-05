package com.olify.eprice.microservice.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.olify.eprice.microservice.component.UserRegistrar;
import com.olify.eprice.microservice.model.OlifyUser;
import com.olify.eprice.microservice.repository.OlifyUserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrarTest {

	@Mock
	private OlifyUserRepository userRepo;
	private UserRegistrar userRegistrar;
	
	@Before
	public void setUp() throws Exception {
		userRegistrar = new UserRegistrar(userRepo);
	}

	@After
	public void tearDown() throws Exception {
		userRepo = null;
		userRegistrar = null;
	}

	@Test
	public void test_getUserDetails_returnsUserInfo() throws Exception {
		given(userRepo.findByName("moses")).willReturn(
				new OlifyUser(1L, "moses", "emacsone@aol.com", "password", "0773405024", "male", "trader", new Date(1/12/2019)));
		
		OlifyUser user = userRegistrar.getUserDetails("moses");
		assertThat(user.getName()).isEqualTo("moses");
		assertThat(user.getEmail()).isEqualTo("emacsone@aol.com");
		assertThat(user.getPassword()).isEqualTo("password");
		assertThat(user.getPhoneNo()).isEqualTo("0704008863");
		assertThat(user.getSex()).isEqualTo("male");
		assertThat(user.getSpeciality()).isEqualTo("trader");
		assertThat(user.getCreatedAt()).isEqualTo("1/12/2019");
	}
	
	@Test(expected = UserNotFoundException.class)
	public void test_getUserDetails_whenUserNotFound() throws Exception {
		given(userRepo.findByName("moses")).willReturn(null);
		
		userRegistrar.getUserDetails("moses");
	}
}