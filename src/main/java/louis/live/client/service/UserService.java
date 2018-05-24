package louis.live.client.service;

import louis.live.client.mapper.UserMapper;
import louis.live.client.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.getAll();
    }

    public User getOne(String userId){
        return userMapper.getOne(userId);
    }

    public User getByName(String userName){
        return userMapper.findByName(userName);
    }

    public String addUser(User user){
        String ret = "";
        User existUser = new User();
        existUser = getByName(user.getUserName());
        if(existUser == null) {
            try {
                userMapper.insert(user);
                ret = "success";
            }catch(Exception e){
                ret = "error";
            }
        }
        else
            ret = "exist";
        return ret;
    }

    public void updateLoginDate(Map params){
        userMapper.updateLoginDate(params);
    }

    public void deleteUser(Map params){
        userMapper.changeStatus(params);
    }

    public void checkName(Map params){
        userMapper.checkName(params);
    }


}
