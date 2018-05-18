package priv.louis.live.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/")
class IndexController {

    @Value("\${live.server.snapshoturl}")
    private val profile: String = ""

    @Value("\${live.client.title}")
    private val title: String = ""

    @RequestMapping("/")
    fun index(model: Model): String {
        model.addAttribute("title", title);
        return "index";
    }

    @RequestMapping("/login")
    fun login(model: Model): String {
        model.addAttribute("title", title);
        model.addAttribute("login", "login");
        return "index";
    }

    @RequestMapping("/profile")
    @ResponseBody
    fun profile(model: Model): String {
        return this.profile;
    }
}