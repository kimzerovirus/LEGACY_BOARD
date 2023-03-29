package me.kzv.legacyboard.infra.test

import jakarta.persistence.*

@Entity
@Table(name = "mybatis_user")
class MyBatisUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val password: String,
    val email: String,
){

}