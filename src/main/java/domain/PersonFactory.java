package domain;

import java.util.regex.Pattern;

public class PersonFactory extends BaseFactory{
    static String userNameRegex = "hnu.*|HNU.*";
    static String telregex = "177\\d{8}|138\\d{8}|136\\d{8}";


    public static boolean check(Person person) {
        errorCode = "";
        String userName = person.getUserName();
        String name = person.getName();
        Integer age = person.getAge();
        String telenum = person.getTelenum();
        if (userName == null || name == null) {
            errorCode = "username and name can not be null";
        }

        boolean flag = true;
        if (!Pattern.matches(userNameRegex, userName)) {
            errorCode += "User name must start with hnu/HNU\n";
            System.out.println("不匹配? 我草泥马");
            System.out.println(userNameRegex + "  " + userName);
            flag = false;
        } else if (userName.length() > 10) {
            errorCode += "The username is too long\n";
            flag = false;
        }
        if (name != null && name.length() == 0) {
            errorCode += "Name should not be empty\n";
            flag = false;
        }
        if (age != null && (age < 0 || age > 150)) {
            errorCode += "You've entered a wrong age\n";
            flag = false;
        }
        System.out.println("telenum " + telenum);
        if (telenum != null && !"".equals(telenum) && !Pattern.matches(telregex, telenum)) {
            errorCode += "Wrong telephone number format";
            flag = false;
        }
        return flag;
    }

    public static Person getPerson(String userName, String name, String age, String telenum) {
        Integer agenum = null;
        try {
            agenum = Integer.parseInt(age);
        } catch (Throwable e) {
            errorCode = "age is not a number";
        }
        if ("".equals(telenum))
            telenum = null;
        if ("".equals(userName))
            userName = null;
        if ("".equals(name))
            name = null;
        Person person = new Person(userName, name, agenum, telenum);
        if (check(person))
            return person;
        else return null;
    }
}
