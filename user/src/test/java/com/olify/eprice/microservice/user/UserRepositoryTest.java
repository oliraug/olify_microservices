/**
 * 
 */
package com.olify.eprice.microservice.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.olify.eprice.microservice.model.OlifyUser;
import com.olify.eprice.microservice.repository.OlifyUserRepository;

/**
 * @author Olify
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private OlifyUserRepository mockRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Before
	public void setup() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		mockRepository = null;
		entityManager = null;
	}

	@Test
	public void test_getUser_returnsUserDetails() throws Exception {
		
		OlifyUser savedUser = entityManager.persistAndFlush(
				new OlifyUser(1L, "moses", "emacsone@aol.com", "password", "0773405024", "male", "trader", new Date(1/12/2019) ));
		OlifyUser user = mockRepository.findByName("moses");
		
		assertThat(user.getName()).isEqualTo(savedUser.getName());
		assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
		assertThat(user.getPassword()).isEqualTo(savedUser.getPassword());
		assertThat(user.getPhoneNo()).isEqualTo(savedUser.getPhoneNo());
		assertThat(user.getSex()).isEqualTo(savedUser.getSex());
		assertThat(user.getSpeciality()).isEqualTo(savedUser.getSpeciality());
		assertThat(user.getCreatedAt()).isEqualTo(savedUser.getCreatedAt());		
	}
}