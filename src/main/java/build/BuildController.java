package build;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class BuildController {
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String getBuild() {
        return "Build 0.0.1";
    }
}
