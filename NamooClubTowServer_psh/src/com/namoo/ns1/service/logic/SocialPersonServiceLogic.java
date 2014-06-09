package com.namoo.ns1.service.logic;

import java.util.List;
import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.exception.NamooClubException;
import com.namoo.ns1.service.facade.SocialPersonService;
import dom.SocialPerson;

public class SocialPersonServiceLogic implements SocialPersonService {

	private EntityManager em;

	public SocialPersonServiceLogic() {
		//
		this.em = EntityManager.getInstance();
	}

	@Override
	public SocialPerson registPerson(String name, String email, String password)
			throws NamooClubException {
		//
		SocialPerson person = em.find(SocialPerson.class, email);
		if (person != null && person.getEmail().equals(email)) {
			throw new NamooClubException("동일한 이메일이 있습니다.");
		}

		person = new SocialPerson(name, email, password);
		em.store(person);
		return em.find(SocialPerson.class, email);

	}

	@Override
	public SocialPerson findPerson(String email) {
		//
		return em.find(SocialPerson.class, email);
	}

	@Override
	public boolean loginUser(String email, String password) throws NamooClubException {
		//
		SocialPerson person = em.find(SocialPerson.class, email);
		if (person == null) { // 이메일이 존재하지 않는 경우
			throw new NamooClubException("이메일이 존재하지 않습니다.");
		} else if (!person.getPassword().equals(password)) { // 패스워드가 틀린 경우
			throw new NamooClubException("비밀번호가 옳지않습니다.");
		}
		return true;

		// return em.find(SocialPerson.class,
		// email).getPassword().equals(password) ? true : false; //삼항연산자
	}

	@Override
	public List<SocialPerson> findAllTowners() {
		//
		return em.findAll(SocialPerson.class);
	}
}
