package me.kzv.legacyboard.mapper

import me.kzv.legacyboard.entity.MyBatisUser
import org.apache.ibatis.annotations.*

@Mapper
interface MyBatisUserMapper {
//    @Options(useGeneratedKeys = true, keyProperty = "id") // 엔티티에서 auto_increment 설정해둬서 없어도 되는듯?
    @Insert("INSERT INTO mybatis_user(username, password, email) VALUES(#{username}, #{password}, #{email})")
    fun insertUser(user: MyBatisUser)

    @Update("UPDATE mybatis_user PASSWORD = #{password}, email = #{email} WHERE id = #{id}")
    fun updateUser(user: MyBatisUser)

    @Delete("DELETE mybatis_user WHERE id = #{id}")
    fun deleteUser(user: MyBatisUser)

    @Select("SELECT * FROM mybatis_user WHERE username = #{username}")
    fun getUser(user: MyBatisUser): MyBatisUser

    @Select("SELECT * FROM mybatis_user WHERE username = #{username}")
    fun getUserList(): MutableList<MyBatisUser>
}