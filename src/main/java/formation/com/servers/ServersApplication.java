package formation.com.servers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.Arrays;
import formation.com.servers.model.*;
import formation.com.servers.enumeration.Status;
import org.springframework.boot.*;
import formation.com.servers.repo.ServerRepo;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;



@SpringBootApplication
public class ServersApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServersApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.161", "Ubuntu linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.162", "Ubuntu linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server3.png", Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.163", "Ubuntu linux", "16 GB", "Personal PC", "http://localhost:8080/server/image/server4.png", Status.SERVER_DOWN));
	};
}

	@Bean
	public CorsFilter corsfilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-with",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration
				.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jurt-Token", "Authorization",
						"Access-Control-Allow-Origin", "Access-Control-Allow-Origin",
						"Access-Control-Allow-Credentials", "Filenane"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}