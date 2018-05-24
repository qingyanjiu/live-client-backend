package louis.live.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
@RefreshScope
//@PropertySource(value = {"classpath:application.properties"},encoding="utf-8")
public class IndexController {

    @Value("${live.server.snapshoturl}")
    private String profile;

    @Value("${live.client.title}")
    private String title;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("title",title);
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("title",title);
        model.addAttribute("login","login");
        return "index";
    }

    @RequestMapping("/profile")
    @ResponseBody
    public String profile(Model model){
        return this.profile;
    }
}
