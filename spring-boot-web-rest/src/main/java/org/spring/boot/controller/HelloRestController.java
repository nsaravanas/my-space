package org.spring.boot.controller;

import javax.inject.Inject;

import org.spring.boot.model.User;
import org.spring.boot.service.HelloRestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloRestController {

	@Inject
	private HelloRestService helloRestService;

	@RequestMapping("/")
	public ModelAndView home(ModelAndView modelAndView) {
		modelAndView.setViewName("home");
		modelAndView.addObject("hello", "Saravana");
		return modelAndView;
	}

	@RequestMapping(value = "/users/", method = RequestMethod.GET)
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Iterable<User> listAllUsers() {
		return this.helloRestService.findAllUsers();
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public User getUser(@PathVariable int userId) {
		return this.helloRestService.getUser(userId);
	}

	@RequestMapping(value = "/users/addUser/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createUser(@RequestBody User user) {
		this.helloRestService.save(user);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public User updateUser(@PathVariable int userId, @RequestBody User user) {
		return this.helloRestService.update(userId, user);
	}

	@RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@PathVariable int userId) {
		this.helloRestService.delete(userId);
	}

	@RequestMapping(value = "/users/", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll() {
		this.helloRestService.deleteAll();
	}

}
