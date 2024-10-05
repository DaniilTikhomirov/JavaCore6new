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
                    List.of("/employee/add?firstName=Ivan&lastName=Ivanov&department=1&salary=25000")));

    public String getAllInfo() {
        StringBuilder info = new StringBuilder("все страница и информация как их использывать:\n");
        for (String s : pages) {

            StringBuilder pages = new StringBuilder();
            for (String s1 : infoPage.get(s).get("pages")) {
                pages.append("| ").append(s1).append("| ");
            }

            StringBuilder fields = new StringBuilder();
            for (String s1 : infoPage.get(s).get("fields")) {
                fields.append("| ").append(s1).append("| ");
            }

            StringBuilder exempl = new StringBuilder();
            for (String s1 : infoPage.get(s).get("exempl")) {
                exempl.append("| ").append(s1).append("| ");
            }

            info.append(String.format("страницы %s <br><br>" +
                    "   методы: %s<br><br>" +
                    "   поля ввода: %s<br><br>" +
                    "       пример: %s<br><br>", s, pages.toString(), fields.toString(), exempl.toString()));
        }
        return info.toString();
    }


}
