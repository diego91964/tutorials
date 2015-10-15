package br.uaijug.tutorial.configuration;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.HttpMethod;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.PooledDataSource;

@Configuration
public class InfrastructureConfiguration {

	private static final Log log = LogFactory.getLog(InfrastructureConfiguration.class);
	
	@Autowired
	Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "br.ufu.cti.guatambu.domain" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());

		return em;
	}

	@Bean
	public EntityManager entityManager() {
		return entityManagerFactory().getObject().createEntityManager();
	}
	
	Properties additionalJpaProperties() {
		Properties properties = new Properties();
		// properties.setProperty("hibernate.hbm2ddl.auto", "validate");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.query.substitutions", "true 1, false 0, yes 'Y', no 'N'");
		//properties.setProperty("hibernate.current_session_context_class", "thread");

		return properties;
	}

	@Bean
    public ComboPooledDataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
	        dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("c3p0.max_size")));
	        dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("c3p0.min_size")));
	        dataSource.setAcquireIncrement(Integer.parseInt(env.getProperty("c3p0.acquire_increment")));
	        dataSource.setIdleConnectionTestPeriod(Integer.parseInt(env.getProperty("c3p0.idle_test_period")));
	        dataSource.setMaxStatements(Integer.parseInt(env.getProperty("c3p0.max_statements")));
	        dataSource.setJdbcUrl(env.getProperty("db.url"));
	        dataSource.setPassword(env.getProperty("db.password"));
	        dataSource.setUser(env.getProperty("db.username"));
			dataSource.setDriverClass(env.getProperty("db.driver"));
		} catch (PropertyVetoException e) {
			log.error("Erro ao montar o datasource",e);
		}
        return dataSource;
    }

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {
 
	@Autowired
	PooledDataSource dataSource;
 
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
	  
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("SELECT CREDENCIAL_APLICACAO AS username, SENHA_APLICACAO AS password, IND_ATIVO AS enabled FROM CREDENCIAIS_APLICACOES WHERE CREDENCIAL_APLICACAO=?")
			.authoritiesByUsernameQuery("SELECT A.CREDENCIAL_APLICACAO AS username, B.ACRONIMO AS role FROM REDENCIAIS_APLICACOES A, PAPEIS_APLICACOES B, PAPEIS_CREDENCIAIS C WHERE A.ID_CREDENCIAL_APLICACAO = C.ID_CREDENCIAL_APLICACAO AND B.ID_PAPEL_APLICACAO = C.ID_PAPEL_APLICACAO AND A.CREDENCIAL_APLICACAO=?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
 
//  @Bean
//  UserDetailsService userDetailsService() {
//    return new UserDetailsService() {
// 
//      @Override
//      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account account = accountRepository.findByUsername(username);
//        if(account != null) {
//        return new User(account.getUsername(), account.getPassword(), true, true, true, true,
//                AuthorityUtils.createAuthorityList("USER"));
//        } else {
//          throw new UsernameNotFoundException("could not find the user '"
//                  + username + "'");
//        }
//      }
//    };
//  }
}
 
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //http.authorizeRequests().anyRequest().fullyAuthenticated().and().
    //httpBasic().and().
    //csrf().disable();
	  
	  http.httpBasic().and().authorizeRequests().//
		antMatchers(HttpMethod.POST, "/employees").hasRole("ADMIN").//
		antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN").//
		antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN").and().//
		csrf().disable();
	  
	  
	  //http.exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());
	  
  }
  
//  @ExceptionHandler(AccessDeniedException.class)
//  void handleBadRequests(HttpServletResponse response) throws IOException {
//      response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again and with a non empty string as 'name'");
//  }
}