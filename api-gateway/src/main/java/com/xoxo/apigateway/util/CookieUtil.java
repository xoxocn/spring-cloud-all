//package com.xoxo.apigateway.util;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @Package com.xoxo.user.util
// * @Description
// * @Author xiehua@zhongshuheyi.com
// * @Date 2018-12-18 12:00
// */
//public class CookieUtil {
//
//    public static void set(HttpServletResponse response,
//                           String name,
//                           String value,
//                           Integer max) {
//        Cookie cookie = new Cookie(name,value);
//        cookie.setPath("/");
//        cookie.setMaxAge(max);
//        response.addCookie(cookie);
//    }
//
//    public static Cookie get(HttpServletRequest request,String name){
//        Cookie[] cookies = request.getCookies();
//        if(cookies==null){
//            return null;
//        }
//        for (Cookie cookie:
//             cookies) {
//            if(name.equals(cookie.getName())){
//                return cookie;
//            }
//        }
//        return null;
//    }
//}
