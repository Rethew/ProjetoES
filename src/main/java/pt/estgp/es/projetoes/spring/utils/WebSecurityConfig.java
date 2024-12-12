package pt.estgp.es.projetoes.spring.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pt.estgp.es.projetoes.spring.controllers.JwtRequestFilter;

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
@EnableMethodSecurity(jsr250Enabled = false,prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //Adiciona o filtro ao pipeline no inicio
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                //não permite nada a não ser os urls definidos e qualquer um dos outros desde que autenticado
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/",
                                "/home",
                                "/authenticate",
                                "/api/v1/meu/alunoPost",
                                "/api/v1/hello",
                                "/api/v1/alunosArgs/*"
                                //,"/greeting"
                        ).permitAll()
                        .anyRequest().permitAll()
                )
                //desativa a proteção contra ataques CSRF senão em modo de testes teriamos de estar constantemente a enviar um token criado manualmente com o POSTMAN
                .csrf(conf->conf.disable())

                //define a chamada que faz Login e permite-a
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                //permite fazer logout de qualquer origem
                .logout((logout) -> logout.permitAll())

                //tratamento de excepções e falhas de autenticação
                .exceptionHandling(exp -> exp.authenticationEntryPoint(jwtAuthenticationEntryPoint) )
                ;


        return http.build();
    }

    /*@Bean
    public AuthenticationManager authenticationManager(List<AuthenticationProvider> myAuthenticationProviders) {
        return new ProviderManager(myAuthenticationProviders);
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return new CustomAuthenticationManager();
    }



    @Bean
    public UserDetailsService userDetailsService() {
        return jwtUserDetailsService;
        /*

         Exemplo de criação de um user detail com um user constante em memória

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);*/
    }

    /**
     * @Autowired
     *        private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
     *
     *    @Autowired
     *    private UserDetailsService jwtUserDetailsService;
     *
     *    @Autowired
     *    private JwtRequestFilter jwtRequestFilter;
     *
     *    @Autowired
     *    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
     * 		// configure AuthenticationManager so that it knows from where to load
     * 		// user for matching credentials
     * 		// Use BCryptPasswordEncoder
     * 		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
     *    }
     *
     *    @Bean
     *    public PasswordEncoder passwordEncoder() {
     * 		return new BCryptPasswordEncoder();
     *    }
     *
     *    @Bean
     *    @Override
     *    public AuthenticationManager authenticationManagerBean() throws Exception {
     * 		return super.authenticationManagerBean();
     *    }
     *
     *    @Override
     *    protected void configure(HttpSecurity httpSecurity) throws Exception {
     * 		// We don't need CSRF for this example
     * 		httpSecurity.csrf().disable()
     * 				// dont authenticate this particular request
     * 				.authorizeRequests().antMatchers("/authenticate").permitAll().
     * 				// all other requests need to be authenticated
     * 				anyRequest().authenticated().and().
     * 				// make sure we use stateless session; session won't be used to
     * 				// store user's state.
     * 				exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
     * 				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
     *
     * 		// Add a filter to validate the tokens with every request
     * 		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
     *    }
     */

      /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }*/


    /**
     *   @Bean
     *   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
     *     http.csrf(csrf -> csrf.disable())
     *         .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
     *         .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
     *         .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
     *         .requestMatchers("/api/test/**").permitAll()
     *         .anyRequest().authenticated());
     *
     *     // http....;
     *
     *     return http.build();
     *   }
     * @return
     */



    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_SUPER > ROLE_ADMIN \n ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    static MethodSecurityExpressionHandler methodSecurityExpressionHandler(RoleHierarchy roleHierarchy) {

        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }




}