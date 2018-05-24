package louis.live.client.controller;

import louis.live.client.service.UserService;
import louis.live.client.uitls.Tools;
import louis.live.client.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public List<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping("/{userId}")
    @ResponseBody
    public User getOne(@PathVariable("userId") String userId) {
        return userService.getOne(userId);
    }

    @RequestMapping("/findByName/{userName}")
    @ResponseBody
    public User getByName(@PathVariable("userName") String userName) {
        return userService.getByName(userName);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map add(String userName, String password) {
        Map result = new HashMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        User user = new User();
        user.setId(Tools.generateUUID());
        user.setUserName(userName);
        user.setPassword(Tools.encoderByMd5(password));
        user.setStatus("1");
        user.setLastLoginDate(sdf.format(new Date()));
        user.setRegisterDate(sdf.format(new Date()));
        String ret = userService.addUser(user);
        result.put("result", ret);
        return result;
    }

    @RequestMapping("/checkName")
    @ResponseBody
    public void checkName(@RequestBody Map params) {
        userService.checkName(params);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(@RequestBody Map params) {
        userService.deleteUser(params);
    }

}
