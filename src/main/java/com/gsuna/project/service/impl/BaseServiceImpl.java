package com.gsuna.project.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BaseServiceImpl {

	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
