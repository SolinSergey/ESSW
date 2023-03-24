package ru.sber.pm.esswfinalproject.utils;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class DayConverter {
    Map<Integer, String> map = new HashMap<>();

    public DayConverter() {
        map.put(1, "Понедельник");
        map.put(2, "Вторник");
        map.put(3, "Среда");
        map.put(4, "Четверг");
        map.put(5, "Пятница");
        map.put(6, "Суббота");
        map.put(7, "Воскресенье");
    }

    public String getDayName(Integer numberDay) {
        return map.get(numberDay);
    }
}
