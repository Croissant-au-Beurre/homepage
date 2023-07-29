package homepage.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Data;


@Entity
@Data
@Table(name="user_info")
public class LoginEntity {
	@Id
	@Column(name = "user_id")
	private String id;
	
	@Column(name = "password")
	private String password;
}
