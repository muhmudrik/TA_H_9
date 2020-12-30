package apap.tk.SIRekrutmenH9.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/user/add/**").permitAll()
                .antMatchers("/pelamar/**").permitAll()
//                .antMatchers("/user/add/**").hasAnyAuthority("Kepala Bagian", "Kepala Departemen HR")
//                .antMatchers("/pelamar/buat").permitAll()
                .antMatchers("/lowongan/add").hasAnyAuthority("Kepala Departemen HR", "Staff Rekrutmen", "Kepala Bagian")
                .antMatchers("/lowongan/daftarLowongan").hasAnyAuthority("Kepala Departemen HR", "Staff Rekrutmen", "Kepala Bagian", "Pelamar")
                .antMatchers("/lowongan/ubahLowongan/**").hasAnyAuthority("Kepala Departemen HR", "Staff Rekrutmen", "Kepala Bagian")
                .antMatchers("/lowongan/hapus/**").hasAnyAuthority("Kepala Departemen HR", "Kepala Bagian")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    //         auth.inMemoryAuthentication()
    //                 .passwordEncoder(encoder())
    //                 .withUser("sirekrutmen").password(encoder().encode("sirekrutmen"))
    //                 .roles("USER");
    // }

   @Autowired
   private UserDetailsService userDetailsService;

   @Autowired
   public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
   }
}