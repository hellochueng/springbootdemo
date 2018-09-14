package org.com.lzz.lucene;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
public class aController {
    @RequestMapping("/a")
    public String a() {
        return "search";
    }
}