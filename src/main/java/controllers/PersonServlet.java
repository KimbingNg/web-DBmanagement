package controllers;

import dao.DaoFactory;
import dao.PersonDao;
import dao.UserDao;
import domain.Person;
import domain.PersonFactory;
import domain.User;
import exceptions.WrongDataException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
//import java.net.http.HttpResponse;

@Controller
@RequestMapping("/person")
public class PersonServlet {
    boolean checkPerson() {
        return false;
    }

    @ResponseBody
    @RequestMapping("/insert.aspx")
    public Map<String, Object> insert(String username, String name, String age, String telenum) {
        Person person = PersonFactory.getPerson(username, name, age, telenum);
        HashMap<String, Object> ret = new HashMap<>();
        String msg;
        if (person == null) {
            ret.put("msg", PersonFactory.getErrorCode());
            return ret;
        }
        PersonDao personDao = DaoFactory.getPersonDao();
        UserDao userDao = DaoFactory.getUserDao();
//        boolean contains = personDao.contains(person);
//        System.out.println(contains);
//        ret.put("msg", Boolean.toString(contains));
        try {
            if (userDao.findByUsername(person.getUserName()) == null)
                userDao.add(new User(person.getUserName(), "123456"));
            personDao.add(person);
            msg = "成功插入:\n" +
                    "username:\t" + username + "\n" +
                    "name:\t" + name + "\n" +
                    "age:\t" + age + "\n" +
                    "teleno:\t" + telenum + "\n";
        } catch (WrongDataException e) {
            e.printStackTrace();
            msg = "插入失败, 请检查您的数据";
        }
        ret.put("msg", msg);
        return ret;
    }

    @ResponseBody
    @RequestMapping("/remove.aspx")
    public Map<String, Object> remove(String username, String name, String age, String telenum) {
        Person person = PersonFactory.getPerson(username, name, age, telenum);
        HashMap<String, Object> ret = new HashMap<>();
        String msg;
        if (person == null) {
            ret.put("msg", PersonFactory.getErrorCode());
            return ret;
        }

        PersonDao personDao = DaoFactory.getPersonDao();
        try {
            personDao.remove(person);
            msg = "成功删除:\n" +
                    "username:\t" + username + "\n" +
                    "name:\t" + name + "\n" +
                    "age:\t" + age + "\n" +
                    "teleno:\t" + telenum + "\n";
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
            DaoFactory.getPersonDao().clear();
        } catch (Throwable e) {
            ret.put("msg", "删除失败");
        }
        ret.put("msg", "删除成功");
        return ret;

    }
}
