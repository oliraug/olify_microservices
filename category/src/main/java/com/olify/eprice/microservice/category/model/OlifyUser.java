/**
 * 
 */
package com.olify.eprice.microservice.category.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Olify
 *
 */
@Embeddable
public class OlifyUser{
	private Long id;
    private String email;
	private String password;	
	private boolean enabled = true;	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "OlifyUser [id=" + id + ", email=" + email + ", password=" + password + ", enabled=" + enabled
				+ ", profileImg=" + profileImg + ", accountNonExpired=" + accountNonExpired + ", accountNonLocked="
				+ accountNonLocked + "]";
	}
}