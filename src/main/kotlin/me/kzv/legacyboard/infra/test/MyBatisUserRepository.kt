package me.kzv.legacyboard.infra.test

import me.kzv.legacyboard.infra.test.MyBatisUser
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.stereotype.Repository

@Repository
class MyBatisUserRepository(
    private val sql: SqlSessionTemplate
) {
    /**
     * 매퍼에 등록한 메서드명을 statement에 넣어주면 된다.
     */
    fun insertUser(user: MyBatisUser): Int {
        return sql.insert("insertUser", user)
    }

    fun updateUser(user: MyBatisUser): Int {
        return sql.update("updateUser", user)
    }

    fun deleteUser(user: MyBatisUser): Int {
        return sql.delete("deleteUser", user)
    }

    fun getUser(user: MyBatisUser): MyBatisUser {
        return sql.selectOne("getUser", user)
    }

    fun getUserList(): MutableList<MyBatisUser> {
        return sql.selectList("getUserList")
    }
}