package org.playground.jmoney.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;

/**
 * We override this class because we need an additional attribute to use in the queries: the userid
 */
@Getter
@Setter
public class JMoneyUser extends User {

	private static final long serialVersionUID = 2607706564454619610L;
	
	private Long id;
	
	public JMoneyUser(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
		this.id = id;
	}

}
