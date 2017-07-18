package ch.renewinkler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Value("${oauth.resource:http://localhost:8082}")
	private String baseUrl;

	@Value("${oauth.authorize:http://localhost:8082/oauth/authorize}")
	private String authorizeUrl;

	@Value("${oauth.token:http://localhost:8082/oauth/token}")
	private String tokenUrl;

	@Value("${oauth.clientId:my-trusted-client}")
	private String clientId;

	@Autowired
	private OAuth2RestOperations restTemplate;

	@RequestMapping("/hello")
	public String hello() {
		return restTemplate.getForObject(baseUrl + "/hello", String.class);
	}

	@Bean
	public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
		return new OAuth2RestTemplate(resource(), oauth2ClientContext);
	}

	@Bean
	protected OAuth2ProtectedResourceDetails resource() {
		AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
		resource.setAccessTokenUri(tokenUrl);
		resource.setUserAuthorizationUri(authorizeUrl);
		resource.setClientId(clientId);
		return resource ;
	}

}
