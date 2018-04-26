package com.alachao.study.tag;

/**
 * @Author yuliang-ds1
 * @Date 14:22  2018/4/26.
 * @Desciption
 */
public class User {

    private String  userName;
    private String  email;


    public  User(){
        System.out.println("User-初始化");
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        System.out.println("设置User-userName:"+userName);
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        System.out.println("设置User-email:"+email);
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


