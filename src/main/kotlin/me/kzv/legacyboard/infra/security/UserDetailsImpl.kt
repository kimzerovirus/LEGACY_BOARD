package me.kzv.legacyboard.infra.security

import me.kzv.legacyboard.member.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDetailsImpl(
    val member: Member
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        // PREFIX 조심
//        val roleList = mutableListOf<GrantedAuthority>()
//        roleList.add(GrantedAuthority { "ROLE_" + this.member.role })
//        return roleList

        return Collections.singleton(SimpleGrantedAuthority("ROLE_" + member.role))
    }

    override fun getPassword(): String {
        return member.password
    }

    override fun getUsername(): String {
        return member.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}