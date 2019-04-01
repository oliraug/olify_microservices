/**
 * 
 */
package com.olify.eprice.microservice.category.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;

/**
 * @author Olify
 *
 */
@Embeddable
public class OlifyUser{
	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")*/
	@Column(name="user_id")
	private Long userId;	
	@Column(name="email", nullable=false)
	@Email
    private String email;
	@Column(name="password")
	private String password;
	@Column(name="enabled")
	private boolean enabled = true;	
	@Column(name="profileImg")
	private String profileImg;		
	@Column(name="not_expired")
	private boolean accountNonExpired;	
	@Column(name="not_locked")
	private boolean accountNonLocked;
	
	public OlifyUser() {
	 super();
	}

	public OlifyUser(String email, String password, boolean enabled, String profileImg, boolean accountNonExpired,
			boolean accountNonLocked) {
		super();
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.profileImg = profileImg;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userId).append(email).append(password).append(enabled).append(profileImg)
				.append(accountNonExpired).append(accountNonLocked);
		return builder.toString();
	}
}