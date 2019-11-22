//package service;
//
//import dao.DaoFactory;
//import dao.UserDao;
//import domain.Person;
//import exceptions.UserException;
//
//
//public class UserService {
//    private UserDao userDao = DaoFactory.getUserDao();
//
//    public void register(Person user) throws UserException {
//        Person existUser = userDao.findByKeyName(user.getUsername());
//        if (existUser != null)
//            throw new UserException("用户名" + user.getUsername() + "已被注册");
//        userDao.add(user);
//    }
//
//    public Person login(Person user) throws UserException {
//        Person existUser = userDao.findByKeyName(user.getUsername());
//        if (existUser == null) {
//            throw new UserException("用户名不存在");
//        }
//        if (!user.getPassword().equals(existUser.getPassword())) {
//            throw new UserException("密码错误");
//        }
//        return existUser;
//    }
//}
