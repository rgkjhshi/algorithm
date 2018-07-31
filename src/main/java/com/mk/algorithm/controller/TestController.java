package com.mk.algorithm.controller;

import com.mk.algorithm.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author song.shi
 * @since 2018-07-11
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * 测试首页
     */
    @RequestMapping(value = "index.api")
    public BaseResponse getMainInfo() {
        return new BaseResponse("0000", "成功");
    }

}
