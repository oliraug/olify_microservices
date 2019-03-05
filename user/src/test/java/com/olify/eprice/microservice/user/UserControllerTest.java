/**
 * 
 */
package com.olify.eprice.microservice.user;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.olify.eprice.microservice.component.UserRegistrar;
import com.olify.eprice.microservice.controller.UserController;
import com.olify.eprice.microservice.model.OlifyUser;

/**
 * @author Olify
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UserRegistrar userRegistrar;

	@Test
	public void getUser_shouldReturnUser() throws Exception{
		given(userRegistrar.getUserDetails(anyString())).willReturn(
				new OlifyUser(1L, "moses", "emacsone@aol.com", "password", "0773405024", "male", "trader", new Date(1/12/2019) ));
		mockMvc.perform(get("/users/moses"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("name").value("moses"));		
	}
	
	@Test
	public void getUser_notFound() throws Exception{
		given(userRegistrar.getUserDetails(anyString())).willThrow (new UserNotFoundException());
		mockMvc.perform(get("/users/moses"))
		.andExpect(status().isNotFound());		
	}
}