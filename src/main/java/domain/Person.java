package domain;


import java.util.regex.Pattern;

public class Person {
    private String userName;
    private String name;
    private Integer age;
    private String telenum;

    private String errorCode = "";


    public String getErrorCode() {
        return errorCode;
    }

    public Person(String userName, String name, Integer age, String telenum) {
        this.userName = userName;
        this.name = name;
        this.age = age;
        this.telenum = telenum;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelenum() {
        return telenum;
    }

    public void setTelenum(String telenum) {
        this.telenum = telenum;
    }
}
