package louis.live.client.controller;

import louis.live.client.service.LiveService;
import louis.live.client.vo.Live;
import louis.live.client.vo.LiveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/live")
@RefreshScope
public class LiveController {

    @Autowired
    LiveService liveService;

    @Value("${live.server.snapshoturl}")
    private String snapshotUrl;

    @Value("${live.server.streamUrl}")
    private String streamUrl;
    
    @Value("${live.server.httpflvUrl}")
    private String httpflvUrl;
    
    @Value("${live.server.hlsUrl}")
    private String hlsUrl;

    @RequestMapping("/")
    @ResponseBody
    public List<LiveInfo> getAll() {
        return liveService.getAll();
    }

    @RequestMapping("/toList")
    public String toList(Model model) {
        List<LiveInfo> lives = new ArrayList();
        lives = liveService.getAll();
        String title = "直播列表";
        model.addAttribute("lives", lives);
        model.addAttribute("title", title);
        model.addAttribute("snapshotUrl", snapshotUrl);
        return "live_list";
    }

    @RequestMapping("/userpage")
    public String myroom(Model model, HttpServletRequest request) {
        Map params = new HashMap();
        LiveInfo live = new LiveInfo();
        String userName = request.getUserPrincipal().getName();
        params.put("userName", userName);
        live = liveService.getActiveLiveOfUser(params);
        String title = "我的房间";
        model.addAttribute("live", live);
        model.addAttribute("title", title);
        model.addAttribute("streamUrl", streamUrl);
        model.addAttribute("httpflvUrl", httpflvUrl);
        model.addAttribute("hlsUrl", hlsUrl);
        return "user_page";
    }

    @RequestMapping("/show")
    public String toLive(Model model, String userName, String password) {
        String result = "live_room";
        Map params = new HashMap();
        params.put("userName", userName);
        LiveInfo live = new LiveInfo();
        live = liveService.getActiveLiveOfUser(params);
        if (live == null) {
            result = "noLive";
        } else {
            if (password != null) {
                if (password.equals(live.getPassword()))
                    model.addAttribute("pass", true);
                else
                    model.addAttribute("pass", false);
            }
            else{
                if (live.getPassword() == null)
                    model.addAttribute("pass", true);
                else
                    model.addAttribute("pass", false);
            }
            model.addAttribute("live", live);
            model.addAttribute("streamUrl", streamUrl);
            model.addAttribute("httpflvUrl", httpflvUrl);
            model.addAttribute("hlsUrl", hlsUrl);
        }
        return result;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map add(String userName, String liveName) {
        Map result = new HashMap();
        Map params = new HashMap();
        params.put("userName", userName);
        params.put("liveName", liveName);
        try {
            liveService.add(params);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

//    @RequestMapping("/delete")
//    public void delete(Map params){
//        liveService.delete(params);
//    }

    @RequestMapping("/end")
    @ResponseBody
    public Map end(String streamCode) {
        Map result = new HashMap();
        Map params = new HashMap();
        params.put("streamCode", streamCode);
        try {
            liveService.end(params);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping("/history")
    @ResponseBody
    public List<Live> getHistoryLivesOfUser(String userId) {
        Map params = new HashMap();
        params.put("userId", userId);
        return liveService.getHistoryLivesOfUser(params);
    }

    @RequestMapping("/findByName/{liveName}")
    @ResponseBody
    public List<Live> getLiveByName(@PathVariable("liveName") String liveName) {
        Map params = new HashMap();
        params.put("liveName", liveName);
        return liveService.getLiveByName(params);
    }

    @RequestMapping("/updateName")
    @ResponseBody
    public Map updateLiveName(String streamCode, String liveName) {
        Map result = new HashMap();
        Map params = new HashMap();
        params.put("streamCode", streamCode);
        params.put("liveName", liveName);
        try {
            liveService.updateLiveName(params);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public Map updateLivePassword(String streamCode, String password) {
        Map result = new HashMap();
        Map params = new HashMap();
        params.put("streamCode", streamCode);
        params.put("password", password);
        try {
            liveService.updateLivePassword(params);
            result.put("result","success");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("result","error");
        }
        return result;
    }

    @RequestMapping("/danmuList")
    public String danmuList(Model model,HttpServletRequest request) {
        Map params = new HashMap();
        String userName = request.getUserPrincipal().getName();
        params.put("userName", userName);
        LiveInfo live = liveService.getActiveLiveOfUser(params);
        if(live != null){
            model.addAttribute("streamCode",live.getStreamCode());
        }
        return "danmuList";
    }


}
