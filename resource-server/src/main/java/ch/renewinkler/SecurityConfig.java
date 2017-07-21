package ch.renewinkler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /* Note: if your Authorization Server is also a Resource Server then there is
        another security filter chain with lower priority controlling the API resources.
        For those requests to be protected by access tokens you need their paths not to be
        matched by the ones in the main user-facing filter chain, so be sure to include
        a request matcher that picks out only non-API resources in the WebSecurityConfigurer above.*/

        // do not apply http security for api endpoint
        http
                .requestMatchers().antMatchers("/login")
                .and()
                .formLogin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);
    }


}
