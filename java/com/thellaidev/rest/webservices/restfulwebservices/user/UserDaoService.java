package com.thellaidev.rest.webservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static Integer userCount = 0;
	private static List<User> users = new ArrayList<>();
	static {
		users.add(new User(++userCount, "Thellai", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Kratos", LocalDate.now().minusYears(200)));
		users.add(new User(++userCount, "Stephen Hawkings", LocalDate.now().minusYears(30)));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findOne( int id ) {
		
		Predicate<? super User> predicate
					= user -> user.getId().equals(id);
		return users.stream().filter(predicate ).findFirst().orElse(null);
	}

	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public void deleteById(int id) {
		Predicate<? super User> predicate =
					user -> user.getId() == id;
		users.removeIf( predicate );
	}
}
