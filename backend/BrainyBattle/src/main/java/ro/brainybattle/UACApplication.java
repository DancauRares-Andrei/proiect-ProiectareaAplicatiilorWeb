package ro.brainybattle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ro.brainybattle.api.service.QuizService;
import ro.brainybattle.model.Epoch;
import ro.brainybattle.model.Lecture;
import ro.brainybattle.model.User;
import ro.brainybattle.model.UserGameStat;
import ro.brainybattle.repository.EpochRepository;
import ro.brainybattle.repository.LectureRepository;
import ro.brainybattle.repository.UserGameStatRepository;
import ro.brainybattle.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// https://spring.io/guides/gs/relational-data-access/
//@Sql({"/sqls/user_schema.sql"})

@SpringBootApplication
@EnableJpaRepositories()
public class UACApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(UACApplication.class);

	public static void main(String args[]) {
		SpringApplication.run(UACApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;
	@Autowired
	EpochRepository epochRepository;
	@Autowired
	LectureRepository lectureRepository;
	@Autowired
	UserGameStatRepository userGameStatRepository;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World!");
		log.info("Hello World!!!");
		Optional<User>admin=userRepository.findByUsername("admin");
		if(!admin.isPresent()){
			log.info("S-a creat utilizatorul admin pentru ca nu exista.");
			User adminul=new User();
			adminul.setEmail("admin@brainybattle.ro");
			adminul.setUsername("admin");
			adminul.setPassword("ParolaComplicata");
			userRepository.save(adminul);
		}
		Optional<Epoch> optionalEpoch=epochRepository.findEpochByName("Preistorica");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Preistorica");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(1,"Cum a influențat dezvoltarea agriculturii viața umană în perioada preistorică?","Creșterea nomadismului și a vânătorii","Dezvoltarea comunităților sedentare și a așezărilor permanente","Invenția primelor forme de scriere","Creșterea migrației umane spre regiunile polare","Dezvoltarea comunităților sedentare și a așezărilor permanente",epoch1);
			lectureRepository.save(lecture);
			UserGameStat userGameStat=new UserGameStat();
			userGameStat.setScore(0);
			userGameStat.setUser(admin.get());
			userGameStat.setEpoch(epoch1);
			userGameStatRepository.save(userGameStat);
		}
		optionalEpoch=epochRepository.findEpochByName("Antica");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Antica");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(2,"Care război celebru a dus la căderea Troiei în epoca antică?","Războiul Peloponesiac","Războiul Punic","Războiul Troian","Războiul Macedonean","Războiul Troian",epoch1);
			lectureRepository.save(lecture);
		}
		optionalEpoch=epochRepository.findEpochByName("Medievala");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Medievala");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(3,"Ce eveniment important a dus la sfârșitul Războiului de 100 de Ani în epoca medievală?","Tratatul de la Versailles","Marea Schismă de Apus","Bătălia de la Agincourt","Bătălia de la Bosworth","Bătălia de la Bosworth",epoch1);
			lectureRepository.save(lecture);
		}
		optionalEpoch=epochRepository.findEpochByName("Moderna");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Moderna");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(4,"Cine a fost liderul Revoluției Franceze în epoca modernă?","Napoleon Bonaparte","Ludovic al XVI-lea","Maximilien Robespierre","Marie Antoinette","Maximilien Robespierre",epoch1);
			lectureRepository.save(lecture);
		}
		optionalEpoch=epochRepository.findEpochByName("Contemporana");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Contemporana");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(5,"Care a fost prima țară care a lansat un satelit artificial în spațiu în epoca contemporană?","Statele Unite","Uniunea Sovietică","China","Franța","Uniunea Sovietică",epoch1);
			lectureRepository.save(lecture);
		}
	}

}