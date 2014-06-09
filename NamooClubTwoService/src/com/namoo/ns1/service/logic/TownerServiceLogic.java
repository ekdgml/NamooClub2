package com.namoo.ns1.service.logic;

import com.namoo.ns1.data.EntityManager;
import com.namoo.ns1.service.facade.TownerService;

import dom.entity.SocialPerson;

public class TownerServiceLogic implements TownerService {

	private EntityManager em;
	
	public TownerServiceLogic() {
		this.em = EntityManager.getInstance();
	}
	
	@Override
	public boolean loginAsTowner(String email, String password) {
		// 
		SocialPerson towner = em.find(SocialPerson.class, email);
		if (towner != null && towner.getPassword().equals(password)) {
			return true;
		}
		
		return false;
	}

	@Override
	public void registTowner(String name, String email, String password) {
		// 
		if (em.find(SocialPerson.class, email) != null) {
			throw new RuntimeException("해당 주민이 이미 존재합니다. 등록할 수 없습니다.");
		}
		
		SocialPerson towner = new SocialPerson(name, email, password);
		em.store(towner);
	}

	@Override
	public void removeTowner(String email) {
		// 
		em.remove(SocialPerson.class, email);
	}

	@Override
	public SocialPerson findTowner(String email) {
		//
		return em.find(SocialPerson.class, email);
	}

}
