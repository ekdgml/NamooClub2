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
			throw new NamooClubException("������ �̸����� �ֽ��ϴ�.");
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
		if (person == null) { // �̸����� �������� �ʴ� ���
			throw new NamooClubException("�̸����� �������� �ʽ��ϴ�.");
		} else if (!person.getPassword().equals(password)) { // �н����尡 Ʋ�� ���
			throw new NamooClubException("��й�ȣ�� �����ʽ��ϴ�.");
		}
		return true;

		// return em.find(SocialPerson.class,
		// email).getPassword().equals(password) ? true : false; //���׿�����
	}

	@Override
	public List<SocialPerson> findAllTowners() {
		//
		return em.findAll(SocialPerson.class);
	}
}
