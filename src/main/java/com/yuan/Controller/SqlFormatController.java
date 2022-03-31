package com.yuan.Controller;

import com.yuan.Service.SqlFormatService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@Controller
public class SqlFormatController {

    @Autowired
    SqlFormatService sqlFormatService;

    @GetMapping("/sqlformat")
    public String showIndex() {
        return "SqlTool";
    }

    @PostMapping("/transfer")
    @ResponseBody
    public String transfer(@RequestBody Map<String, String> sqlMap) {
        return  sqlFormatService.transfer(sqlMap.get("sql"));
    }
}
