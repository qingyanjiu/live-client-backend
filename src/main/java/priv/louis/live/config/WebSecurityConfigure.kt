//package priv.louis.live.config
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.context.ApplicationContext
//import org.springframework.context.ApplicationContextAware
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.authentication.encoding.Md5PasswordEncoder
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.builders.WebSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import priv.louis.live.service.CustomUserDetailsService
//
//@Configuration
//@EnableWebSecurity
//open class WebSecurityConfigure : WebSecurityConfigurerAdapter(), ApplicationContextAware {
////    protected var applicationContext: ApplicationContext? = null
//
//    @Throws(Exception::class)
//    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/").permitAll()
//                .antMatchers("/user/add").permitAll()
//                .antMatchers("/profile").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .usernameParameter("userName")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/")
//                //                .successHandler(loginSuccessHandler())
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login")
//                //                .logoutSuccessHandler(customLogoutSuccessHandler())
//                .permitAll()
//    }
//
////    @Bean
////    LoginSuccessHandler loginSuccessHandler(){
////        return new LoginSuccessHandler();
////    }
////
////    @Bean
////    CustomLogoutSuccessHandler customLogoutSuccessHandler(){
////        return new CustomLogoutSuccessHandler();
////    }
//
//    @Autowired
//    @Throws(Exception::class)
//    fun configureGlobal(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(customUserDetailsService())
//                //                .passwordEncoder(new PlaintextPasswordEncoder());
//                .passwordEncoder(passwordEncoder())
//    }
//
//    /**
//     * 设置用户密码的加密方式为MD5加密
//     *
//     * @return
//     */
//    @Bean
//    open fun passwordEncoder(): Md5PasswordEncoder {
//        return Md5PasswordEncoder()
//
//    }
//
//    /**
//     * 自定义UserDetailsService，从数据库中读取用户信息
//     *
//     * @return
//     */
//    @Bean
//    open fun customUserDetailsService(): CustomUserDetailsService? {
//        return CustomUserDetailsService()
//    }
//
//    @Throws(Exception::class)
//    override fun configure(web: WebSecurity) {
//        //解决静态资源被拦截的问题
//        web.ignoring().antMatchers("/assets/**")
//        web.ignoring().antMatchers("/home_page/**")
//        web.ignoring().antMatchers("/home_page2/**")
//        web.ignoring().antMatchers("/player/**")
//        web.ignoring().antMatchers("/stylesheets/**")
//    }
//}