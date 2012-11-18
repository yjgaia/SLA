package sla.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserInfo implements UserDetails {
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Date joinDate;

	private Date lastLoginDate;

	private int loginCount;

	public void increaseLoginCount() {
		loginCount++;
	};
	
	private int achieveScore=0;
	public int addAchieveScore(int score){
		return achieveScore+=score;
	}

	// 소셜 유저를 위한 설정
	private boolean socialUser = false;
	private String socialDisplayName;
	private String socialImageUrl;
	private String socialProfileUrl;
	private String socialProviderId;
	private String socialProviderUserId;
	private String socialName;
	private String socialEmail;
	private String socialGender;
	private String socialBirthday;
	private int socialFriendCount;

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		HashSet<GrantedAuthority> hs = new HashSet<GrantedAuthority>();
		hs.add(new GrantedAuthority() {
			private static final long serialVersionUID = 1L;

			@Override
			public String getAuthority() {
				return "ROLE_USER";
			}
		});
		return hs;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Long getUserCount(){
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM UserInfo o",
						Long.class).getSingleResult();
	}

}
