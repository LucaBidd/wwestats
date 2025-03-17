package com.luca.wwestatsspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WwestatsspringApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(WwestatsspringApplication.class, args);
    }
}

/* 
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.luca.wwestatsspring.model.Wrestler;
import com.luca.wwestatsspring.repository.WrestlerRepository;
@Bean
CommandLineRunner runner(WrestlerRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Wrestler wrestler = new Wrestler();
			
			Query query = new Query();
			query.addCriteria(Criteria.where("nome").is("John Cena"));
			
			List<Wrestler> wrestlers = mongoTemplate.find(query, Wrestler.class);
			
			if(wrestlers.size() > 1) {
				throw new IllegalStateException("Trovati ");
				}
				if(wrestlers.isEmpty()){
					System.out.println("Inserimento wrestler " + wrestler);
					repository.insert(wrestler);
					} else{
						System.out.println(wrestler + " già esistente");
						}
						};
						}
						
						
						repository.findWrestlerByNome(nome).ifPresentOrElse(s -> {
							System.out.println(wrestler + " già esistente");
							}, ()->{
		System.out.println("Inserimento wrestler " + wrestler);
				repository.insert(wrestler);
	});
	private void usingMongoTemplateAndQuery(WrestlerRepository repository, MongoTemplate mongoTemplate, String nome, Wrestler wrestler){}
	*/