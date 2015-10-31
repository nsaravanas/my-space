package org.spring.boot.service;

import javax.inject.Inject;

import org.spring.boot.model.User;
import org.spring.boot.repository.HelloRestRepository;
import org.springframework.stereotype.Service;

@Service
public class HelloRestService {

	@Inject
	private HelloRestRepository helloRestRepository;

	public Iterable<User> findAllUsers() {
		return this.helloRestRepository.findAll();
	}

	public User getUser(int userId) {
		return this.helloRestRepository.findOne(userId);
	}

	public void save(User user) {
		this.helloRestRepository.save(user);
	}

	public User update(int userId, User user) {
		User u = this.helloRestRepository.findOne(userId);
		u.setName(user.getName());
		return u;
	}

	public void delete(int userId) {
		this.helloRestRepository.delete(userId);
	}

	public void deleteAll() {
		this.helloRestRepository.deleteAll();
	}

}
