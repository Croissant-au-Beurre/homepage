package homepage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import homepage.dao.LoginDao;
import homepage.entity.LoginEntity;


@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;
	
	public LoginEntity selectUserInfo() {
		return loginDao.selectUserInfo();
	}
}
