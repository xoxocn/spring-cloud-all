package com.xoxo.demo.controller;

import com.xoxo.kafka.client.ShopInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Package com.example.demo.controller
 * @Description
 * @Author xiehua@zhongshuheyi.com
 * @Date 2019-04-17 12:29
 */
@RestController
public class DemoController {
    @Autowired
    private ShopInterface shopInterface;

    //kafka测试接口
    @GetMapping("kafka-test")
    public String kafkaTest(){
        String s = shopInterface.sendShopMessage("i 'm xoxo");
        return s;
    }
}
