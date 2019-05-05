package org.keegsands.sportball.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "tap_softball.user")
public class User implements UserDetails {

	private static final long serialVersionUID = 4091435885513333426L;
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String email;

	private String password;

	private String username;

	private String roles;

	private boolean enabled = true;

	public User() {

	}

	public User(final String username, final String password, final String roles, final String email, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.email = email;
		this.enabled = enabled;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(final String roles) {
		this.roles = roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public void setIsEnabled(final boolean isEnabled) {
		this.enabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
		if (null != getRoles()) {
			final String[] roleSplit = getRoles().split(",");
			for (int ctr = 0; ctr < roleSplit.length; ctr++) {
				auths.add(new SimpleGrantedAuthority(roleSplit[ctr]));
			}
		}
		return auths;
	}

}
