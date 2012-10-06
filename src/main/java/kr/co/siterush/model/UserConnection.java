package kr.co.siterush.model;

import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserConnection {
	
	@Id
	@NotEmpty
	@Column(unique = true)
	private String userId;

}
