package homepage.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.boot.ConfigAutowireable;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.builder.SelectBuilder;
import org.springframework.stereotype.Repository;

import homepage.entity.LoginEntity;

@Repository
@ConfigAutowireable
@Dao
public interface LoginDao {
	
	/**
	* 登录信息
	* @return 登录名和密码
	*/
	default LoginEntity selectUserInfo() {

	Config config = Config.get(this);

	SelectBuilder builder = SelectBuilder.newInstance(config);
	builder.sql("SELECT");
	builder.sql("	*");
	builder.sql("FROM");
	builder.sql(" user_info");

	return builder.getEntitySingleResult(LoginEntity.class);
	}
}
	




