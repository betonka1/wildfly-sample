package explaineverything.wildfly.rest.service;

import javax.ws.rs.core.Response;

import explaineverything.wildfly.ejb.UserEJB;
import explaineverything.wildfly.vo.UserVO;

public class UserService {

	private UserEJB service;

	public Response create(UserVO user) {
		return null;
	}

}