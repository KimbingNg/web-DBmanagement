package domain;

public class UserFactory extends BaseFactory {
    private static boolean check(User user) {
        if (!(user.getPass().length() > 0 && user.getPass().length() <= 16)) {
            errorCode = "密码长度不符";
            return false;
        }
        return true;
    }

    public static User getUser(String name, String pass) {
        if (name == null || pass == null) {
            errorCode = "用户名或密码不能为空";
            return null;
        }
        User user = new User(name, pass);
        if (!check(user))
            return null;
        return user;
    }
}
