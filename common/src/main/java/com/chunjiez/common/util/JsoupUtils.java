package com.chunjiez.common.util;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.net.URI;
import java.util.HashMap;
import java.util.Random;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
public class JsoupUtils {


    public static void main(String[] args) throws Exception {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 " +
                "Safari/537.36");
        headers.put("referer", "http://tuziyouwang.com/");
        int page;
        for (int i = 2; i < 100; i++) {
            page = i;
            Connection.Response response =
                    Jsoup.connect(String.format("http://tuziyouwang.com/gengduo/index_%d.html", page)).method(Connection.Method.GET).execute();
            Document document = response.parse();
            Elements elements = document.getElementsByClass("u-img");
            for (Element element : elements) {
                Node firstChild = element.firstChild();
                String href = firstChild.attr("href");
                System.out.println("href = " + href);
                Connection.Response execute = Jsoup.connect(href).method(Connection.Method.GET).execute();
                Document details = execute.parse();
                Element content = details.select("div.m-list-content>img").first();
                String src = content.attr("src");
                System.out.println("src = " + src);
                String filename = content.attr("alt");
                URI uri = URI.create(href);
                String host = uri.getHost();
                Thread.sleep(new Random().nextInt(1000));
                HttpClientUtils.download(filename + ".jpg", uri.getScheme() + "://" + host + src, headers);
            }
        }

    }
}
