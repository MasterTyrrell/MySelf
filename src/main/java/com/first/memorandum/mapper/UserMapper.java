package com.first.memorandum.mapper;

import com.first.memorandum.entity.User;
import org.apache.ibatis.annotations.*;



public interface UserMapper {

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "userName",column = "userName"),
            @Result(property = "password",column = "password"),
            @Result(property = "mobileNo",column = "mobileNo"),
            @Result(property = "status",column = "status"),
            @Result(property = "imageUrl",column = "imageUrl"),
            @Result(property = "email",column = "email"),
            @Result(property = "delFlag",column = "delFlag"),
            @Result(property = "createTime",column = "createTime"),
            @Result(property = "updateTime",column = "updateTime"),
            @Result(property = "userNo",column = "userNo"),
            @Result(property = "updateTime",column = "updateTime"),
            @Result(property = "status",column = "status")
    })
    @Select("<script>" +
            " SELECT * FROM user " +
            "where 1=1 and  mobileNo = #{mobileNo}  " +
            "<if test='delFlag!=null'>" +
            "AND delFlag = #{delFlag}" +
            "</if>" +
            "<if test='status!=null'>" +
            "AND status = #{status}" +
            "</if>" +
            "<if test='password!=null'>" +
            "AND password = #{password}" +
            "</if>" +
            " limit 1 </script>")
    User getUserByMobileNo(String mobileNo,Boolean delFlag,Integer status,String password);

    @Insert("INSERT INTO user(userNo,userName,password,mobileNo,status,imageUrl,email,delFlag,createTime,updateTime,userType) values(#{userNo},#{userName},#{password},#{mobileNo},#{status},#{imageUrl},#{email},#{delFlag},#{createTime},#{updateTime},#{userType})")
    Integer insertUser(User user);

    @Update("<script>" +
            "UPDATE user SET password=#{newPassword},updateTime=now() WHERE mobileNo = #{mobileNo} " +
            "<if test='oldPassword!=null'>" +
            "AND password = #{oldPassword} " +
            "</if> " +
            "</script>")
    Integer updateUserPassword(String mobileNo,String oldPassword,String newPassword);

    @Update("UPDATE user SET userName = #{userName},updateTime=now() WHERE mobileNo = #{mobileNo} ")
    Integer updateUserName(String userName,String mobileNo);

    @Update("UPDATE user SET email = #{email},updateTime=now() where mobileNo = #{mobileNo}")
    Integer updateEmailByMobileNo(String mobileNo,String email);

    @Select("SELECT userNo from user where mobileNo=#{mobileNo}")
    String getUserNoByMobileNo(String mobileNo);
}
