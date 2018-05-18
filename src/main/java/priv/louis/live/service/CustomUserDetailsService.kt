//package priv.louis.live.service
//
//import org.springframework.security.core.GrantedAuthority
//import java.util.ArrayList
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.security.core.userdetails.UserDetailsService
//import priv.louis.live.mapper.UserMapper
//
//
//class CustomUserDetailsService : UserDetailsService {
//    @Autowired
//    private val userMapper: UserMapper? = null
//
//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(username: String): UserDetails {
//        val user = userMapper?.findByName(username) ?: throw UsernameNotFoundException("UserName $username not found")
//        return object : UserDetails {
//            override fun getAuthorities(): Collection<GrantedAuthority> {
//                val list = ArrayList<GrantedAuthority>()
//                val authority = GrantedAuthority { "ROLE_USER" }
//                list.add(authority)
//                return list
//            }
//
//            override fun getPassword(): String {
//                return user.password
//            }
//
//            override fun getUsername(): String {
//                return user.userName
//        }
//
//            override fun isAccountNonExpired(): Boolean {
//                return true
//            }
//
//            override fun isAccountNonLocked(): Boolean {
//                return true
//            }
//
//            override fun isCredentialsNonExpired(): Boolean {
//                return true
//            }
//
//            override fun isEnabled(): Boolean {
//                return true
//            }
//        }
//
//    }
//}