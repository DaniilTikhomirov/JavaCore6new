package com.core.javacore6.services;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class MajorPageServiceImpl implements MajorPageService{
    private final List<String> pages = List.of("/employee");
    private final Map<String, Map<String, List<String>>> infoPage = Map.of("/employee",
            Map.of("pages",
                    List.of("add", "remove", "find"),
                    "fields",
                    List.of("firstName", "lastName"),
                    "exempl",
                    List.of("/employee/add?firstName=Ivan&lastName=Ivanov ")));

    public String getAllInfo() {
        String info = "все страница и информация как их использывать:\n";
        for (String s : pages) {

            String pages = "";
            for (String s1 : infoPage.get(s).get("pages")) {
                pages += "| " + s1 + "| ";
            }

            String fields = "";
            for (String s1 : infoPage.get(s).get("fields")) {
                fields += "| " + s1 + "| ";
            }

            String exempl = "";
            for (String s1 : infoPage.get(s).get("exempl")) {
                exempl += "| " + s1 + "| ";
            }

            info += String.format("страницы %s <br><br>" +
                    "   методы: %s<br><br>" +
                    "   поля ввода: %s<br><br>" +
                    "       пример: %s<br><br>", s, pages, fields, exempl);
        }
        return info;
    }


}
