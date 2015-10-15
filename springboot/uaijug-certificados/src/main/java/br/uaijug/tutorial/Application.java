package br.uaijug.tutorial;


import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan({ "br.uaijug.tutorial" })
@EnableAutoConfiguration
public class Application {
	
	public static void main(String[] args)  {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

//        String[] beanNames = ctx.getBeanDefinitionNames();
//        Arrays.sort(beanNames);
//        for (String beanName : beanNames) {
//            System.out.println(beanName);
//        }
	}
	
	
//	@Bean
//	CommandLineRunner init(final PasswordEncoder passwordEncoder) {
//      
//		return new CommandLineRunner() {
//
//			@Override
//			public void run(String... arg0) throws Exception {
//				//accountRepository.save(new Account("rbaxter", "password"));
//				System.out.println(passwordEncoder.encode("pc"));
//  			}
//		};
//	}
}