package sk.kasv.ferencak.SportReservation;

import sk.kasv.ferencak.SportReservation.Entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sk.kasv.ferencak.SportReservation.DAO.AppDAO;

@SpringBootApplication
public class SportReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportReservationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

			System.out.println("Creating user");
			createUser(appDAO);
		};
	}

	private void createUser(AppDAO appDAO) {
		System.out.println("Creating a new user object...");
		User tempUser = new User("Peter", "Straka", "straka@email.com");
		System.out.println("Saving the user...");
		appDAO.create(tempUser);
		System.out.println("Saved user. Generated id: " + tempUser.getId());
	}
}
