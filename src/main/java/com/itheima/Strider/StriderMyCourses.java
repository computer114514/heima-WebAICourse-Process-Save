package com.itheima.Strider;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tools.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class StriderMyCourses {
    public static void main(String[] args) throws Exception {
        fetchAllPart();
    }

    public static void fetchAllPart(){

    }
    public static String fetchOnePart(String id) {
        try {
            //获取链接字符串
            String url="https://casb.njit.edu.cn/http/webvpn3e1a11b7208e283ab07ade5d2913fc13d6f6fe09d2dc7372db2a51a14aa4167a/jwglxt/xsxy/xsxyqk_cxJxzxjhxfyqKcxx.html?gnmkdm=N105515&enlink-vpn";
            //建立链接,请求数据
            Connection.Response response = Jsoup.connect(url).header("Cookie", "clientInfo=eyJ1c2VybmFtZSI6IjIwMjI1MDgyMiIsInVzZXJJZCI6IjI4MTVlODJhOGRmNjQ3ZDJiYzI1NjYwNGFlOTljNGYyIiwibG9naW5LZXkiOiJWNmw0VGRxWTlBNGpuVlZPIiwic2lkIjoiZDI5ZGRlMDAtNjZhZS00MzlkLWIzM2MtNzhiMDFiOTMwOTMzIn0=;ENSSESSIONID=ZDI5ZGRlMDAtNjZhZS00MzlkLWIzM2MtNzhiMDFiOTMwOTMz;GUESTSESSIONID=ZTY1ZGViODQtMGVlYy00YTUxLWE0OTAtN2IyNWE4NTQ1N2U1;iPlanetDirectoryPro=fTRhEyuMLXYjbtGOXyBvzg;vpn_timestamp=1769339064")
                    .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")//传递的是表单类型
                    .header("X-Requested-With", "XMLHttpRequest")//ajax请求
                    .header("Referer", "https://casb.njit.edu.cn/http/webvpn3e1a11b7208e283ab07ade5d2913fc13d6f6fe09d2dc7372db2a51a14aa4167a/jwglxt/xsxy/xsxyqk_cxXsxyqkIndex.html?gnmkdm=N105515&layout=default")
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36 Edg/144.0.0.0")
                    //浏览器类型和来源
                    .data("fromXh_id", "")
                    .data("xfyqjd_id", id)
                    .data("xh_id", "202250822")
                    .data("cjlrxn", "2025")
                    .data("cjlrxq", "3")
                    .data("bkcjlrxn", "2025")
                    .data("bkcjlrxq", "3")
                    .data("xscjcxkz", "0")
                    .data("cjcxkzzt", "0")
                    .data("cjztkz", "0")
                    .data("cjzt", "")
                    .ignoreContentType(true)
                    .method(Connection.Method.POST).execute();
            //获取结果
            String body=response.body();

            System.out.println("真的成功了吗");
            System.out.println("内容预览:"+body.substring(0,Math.min(200,body.length()))+"...");

            return body;
        }catch(Exception e){
            System.out.println("oooops！失败了"+e.getMessage());
            return null;
        }
    }
}