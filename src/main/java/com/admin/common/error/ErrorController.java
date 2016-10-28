package com.admin.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-25
 * Time: 上午11:14
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ErrorController {
    @RequestMapping("error")
    public ModelAndView index() {
       return new ModelAndView("error");
    }
}
