package ro.brainybattle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
			admin=userRepository.findByUsername("admin");
		}
		Optional<Epoch> optionalEpoch=epochRepository.findEpochByName("Preistorica");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Preistorica");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(1,"Cum a influențat dezvoltarea agriculturii viața umană în perioada preistorică?","Creșterea nomadismului și a vânătorii","Dezvoltarea comunităților sedentare și a așezărilor permanente","Invenția primelor forme de scriere","Creșterea migrației umane spre regiunile polare","Dezvoltarea comunităților sedentare și a așezărilor permanente",epoch1,"Este prima perioadă majoră a istoriei umane, cuprinzând aproximativ 3.4 milioane de ani. Această epocă este caracterizată de absența scrisului și de utilizarea uneltelor de piatră pentru supraviețuire. Oamenii preistorici trăiau în grupuri mici, se ocupau cu vânătoarea și colectarea hranei și au dezvoltat primele forme de artă rupestră. Epoca Preistorică se încheie odată cu apariția scrisului și dezvoltarea primelor civilizații.");
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
			Lecture lecture=new Lecture(2,"Care război celebru a dus la căderea Troiei în epoca antică?","Războiul Peloponesiac","Războiul Punic","Războiul Troian","Războiul Macedonean","Războiul Troian",epoch1,"Marchează începutul civilizațiilor dezvoltate, inclusiv cele ale Mesopotamiei, Egiptului Antic, Greciei și Romei. În această perioadă, omenirea a făcut progrese  semnificative în domeniile arhitecturii, artelor, științei, filozofiei și politicii.  Epoca Antică a fost martora unor realizări remarcabile, precum construirea marilor  piramide din Egipt și crearea primei democrații în Atena.");
			lectureRepository.save(lecture);
		}
		optionalEpoch=epochRepository.findEpochByName("Medievala");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Medievala");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(3,"Ce eveniment important a dus la sfârșitul Războiului de 100 de Ani în epoca medievală?","Tratatul de la Versailles","Marea Schismă de Apus","Bătălia de la Agincourt","Bătălia de la Bosworth","Bătălia de la Bosworth",epoch1,"A fost o perioadă de tranziție între Antichitate și Epoca Modernă. Această epocă este  caracterizată de feudalism, creștinism  și răspândirea religiei catolice în Europa. Dezvoltarea comerțului și a orașelor a avut  loc treptat, iar societatea medievală a fost divizată în clase sociale clare, cum ar fi  nobilimea, clericatul și țăranii.  Epoca Medievală a fost, de asemenea,  marcată de Războaiele Cruciade  și de Renașterea Carolingiană.");
			lectureRepository.save(lecture);
		}
		optionalEpoch=epochRepository.findEpochByName("Moderna");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Moderna");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(4,"Cine a fost liderul Revoluției Franceze în epoca modernă?","Napoleon Bonaparte","Ludovic al XVI-lea","Maximilien Robespierre","Marie Antoinette","Maximilien Robespierre",epoch1,"Epoca Modernă a fost o perioadă de schimbări radicale și inovații care au revoluționat lumea. Descoperirile geografice,Revoluția Industrială și Renașterea științifică au redefinit societatea și au condus la dezvoltarea științei, tehnologiei și a economiei. Epoca Modernă a fost martora unor evenimente semnificative, cum ar fi Revoluția Franceză, Revoluția Industrială și colonizarea Americii de către europeni.");
			lectureRepository.save(lecture);
		}
		optionalEpoch=epochRepository.findEpochByName("Contemporana");
		if(optionalEpoch.isEmpty()){
			Epoch epoch1=new Epoch();
			epoch1.setName("Contemporana");
			epochRepository.save(epoch1);
			Lecture lecture=new Lecture(5,"Care a fost prima țară care a lansat un satelit artificial în spațiu în epoca contemporană?","Statele Unite","Uniunea Sovietică","China","Franța","Uniunea Sovietică",epoch1,"Este perioada în care trăim în prezent. Aceasta a fost marcată de progresul rapid al tehnologiei, globalizarea și schimbările  sociale semnificative. Evenimente precum cele două războaie mondiale, Revoluția Rusă și Revoluția Digitală au avut  un impact profund asupra societății noastre. În această epocă,  am asistat la dezvoltarea mass-mediei, creșterea economiei globale  și apariția problemelor globale precum schimbările  climatice și inegalitățile sociale.");
			lectureRepository.save(lecture);
		}
	}
}