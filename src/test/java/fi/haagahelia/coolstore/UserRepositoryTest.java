package fi.haagahelia.coolstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.coolstore.domain.User;
import fi.haagahelia.coolstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private UserRepository urepository;

	@Test
	public void findByUsernameShouldReturnUser() {
		User user = urepository.findByUsername("admin");
		List<User> users = new ArrayList<>();
		users.add(user);

		assertThat(users).hasSize(1);
		assertThat(users.get(0).getUsername()).isEqualTo("admin");
	}

	@Test
	public void createNewUser() {
		User user = new User("username", "password", "email", "role");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}

	@Test
	public void deleteNewUser() {
		User finduser = urepository.findByUsername("user");
		List<User> users = new ArrayList<>();
		users.add(finduser);

		User user = users.get(0);
		urepository.delete(user);

		User newUser = urepository.findByUsername("user");
		List<User> newUsers = new ArrayList<>();
		users.add(newUser);
		assertThat(newUsers).hasSize(0);
	}
}