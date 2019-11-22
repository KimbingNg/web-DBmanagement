package controllers;

import dao.DaoFactory;
import dao.PersonDao;
import dao.UserDao;
import domain.User;
import domain.UserFactory;
import exceptions.WrongDataException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/user")
public class UserServlet {

    @ResponseBody
    @RequestMapping("/insert.aspx")
    public Map<String, Object> insert(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        System.out.println("UserServlet.insert");
        User user = UserFactory.getUser(username, password);
        HashMap<String, Object> ret = new HashMap<>();
        String msg;
        if (user == null) {
            ret.put("msg", UserFactory.getErrorCode());
            return ret;
        }
        System.out.println("daozhelile");
        UserDao userDao = DaoFactory.getUserDao();
        try {
            userDao.add(user);
            msg = "成功插入:\n" +
                    "username:\t" + username + "\n" +
                    "pass:\t" + password + "\n";
        } catch (WrongDataException e) {
            e.printStackTrace();
            msg = "插入失败, 请检查您的数据";
        }
        System.out.println(msg);
        ret.put("msg", msg);
        return ret;
    }

    @ResponseBody
    @RequestMapping("/remove.aspx")
    public Map<String, Object> remove(String username, String password) {
        User user = new User(username, password);
        HashMap<String, Object> ret = new HashMap<>();
        String msg;
        UserDao userDao = DaoFactory.getUserDao();
        PersonDao personDao = DaoFactory.getPersonDao();
        try {
            try {
                personDao.removeByUserName(username);
            } catch (SQLException ignored) {
                System.out.println("没删到, 管理员看看怎么回事");
            }
            userDao.remove(user);
            msg = "成功删除:\n" +
                    "username:\t" + username + "\n";
        } catch (WrongDataException e) {
            e.printStackTrace();
            msg = "删除失败, 请检查您的数据";
        }
        ret.put("msg", msg);
        return ret;
    }

    @RequestMapping("/clear.aspx")
    public @ResponseBody
    Map<String, String> clear() {
        Map<String, String> ret = new TreeMap<>();
        try {
            DaoFactory.getUserDao().clear();
        }catch (Throwable e){
            ret.put("msg", "需要先删除person");
            return ret;
        }
        ret.put("msg", "成功!");
        return ret;
    }
}
