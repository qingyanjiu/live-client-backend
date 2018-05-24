package louis.live.client.mapper;

import louis.live.client.vo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    @Select("SELECT * FROM user_info")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userName",  column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "registerDate", column = "register_date"),
            @Result(property = "lastLoginDate", column = "last_login_date"),
            @Result(property = "trueNameCert", column = "true_name_cert"),
            @Result(property = "trueName", column = "true_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "status", column = "status")
    })
    List<User> getAll();

    @Select("SELECT * FROM user_info WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userName",  column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "registerDate", column = "register_date"),
            @Result(property = "lastLoginDate", column = "last_login_date"),
            @Result(property = "trueNameCert", column = "true_name_cert"),
            @Result(property = "trueName", column = "true_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "status", column = "status")
    })
    User getOne(String userId);


    @Select("SELECT * FROM user_info WHERE username = #{username}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "userName",  column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "nick", column = "nick"),
            @Result(property = "registerDate", column = "register_date"),
            @Result(property = "lastLoginDate", column = "last_login_date"),
            @Result(property = "trueNameCert", column = "true_name_cert"),
            @Result(property = "trueName", column = "true_name"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "email", column = "email"),
            @Result(property = "status", column = "status")
    })
    User findByName(String username);

    @Insert("INSERT INTO user_info VALUES(#{id},#{userName},#{password},#{gender},#{nick}," +
            "#{registerDate},#{lastLoginDate},#{trueNameCert},#{trueName},#{phoneNumber},#{email},#{status})")
    void insert(User user);

    @Update("update user_info set last_login_date=#{date} where id=#{id}")
    void updateLoginDate(Map params);

    @Update("update user_info set status=#{status} where id=#{id}")
    void changeStatus(Map params);

    @Select("select count(id) count from user_info where username=#{userName}")
    int checkName(Map params);
}
