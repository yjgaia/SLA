package sla.model;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Page {

	@NotEmpty
	@Size(max = 500)
	@Column(length = 500, nullable = false)
	private String title;

	@Size(max = 3000)
	@Column(length = 3000)
	private String content;
	
	@NotEmpty
	@Size(min = 4, max = 20)
	@Column(length = 40, nullable = false)
	// 암호화 하면 암호의 길이 증가
	private String password;
	
}
