package louis.live.client.service;

import louis.live.client.mapper.LiveMapper;
import louis.live.client.mapper.UserMapper;
import louis.live.client.uitls.Tools;
import louis.live.client.vo.Live;
import louis.live.client.vo.LiveInfo;
import louis.live.client.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiveService {
    @Autowired
    LiveMapper liveMapper;
    @Autowired
    UserMapper userMapper;

    public List<LiveInfo> getAll(){
        return liveMapper.getAll();
    }

    public void add(Map params) throws Exception{
        String id = Tools.generateUUID();
        String streamCode = Tools.generateUUID();
        User user = userMapper.findByName(params.get("userName").toString());
        if(user == null){
            throw new Exception("开启直播时未匹配到用户");
        }
        Live live = new Live();
        live.setId(id);
        live.setLiveName(params.get("liveName").toString());
        live.setUserId(user.getId());
        live.setStreamCode(streamCode);
        liveMapper.add(live);
    }

//    public void delete(Map params){
//        liveMapper.delete(params);
//    }

    public void end(Map params) throws Exception{
        try {
            liveMapper.end(params);
        }catch(Exception e){
            throw new Exception("修改直播间名出错");
        }
    }

    public LiveInfo getActiveLiveOfUser(Map params){
        LiveInfo live = liveMapper.getActiveLiveOfUser(params);
        if(live == null) {
            live = new LiveInfo();
            live.setUserName(params.get("userName").toString());
        }
        return live;
    }

    public List<Live> getHistoryLivesOfUser(Map params){
        return liveMapper.getHistoryLivesOfUser(params);
    }

    public List<Live> getLiveByName(Map params){
        return liveMapper.getLiveByName(params);
    }

    public void updateLiveName(Map params) throws Exception{
        try {
            liveMapper.updateLiveName(params);
        }catch(Exception e){
            throw new Exception("修改直播间名出错");
        }
    }

    public void updateLivePassword(Map params) throws Exception{
        try {
        liveMapper.updateLivePassword(params);
        }catch(Exception e){
            throw new Exception("修改直播间密码出错");
        }
    }
}
