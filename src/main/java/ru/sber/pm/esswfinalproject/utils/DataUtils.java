package ru.sber.pm.esswfinalproject.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data

@RequiredArgsConstructor
@Component
public class DataUtils {
    public String parsingDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateGrade = formatter.format(date);
        return dateGrade;
    }
}
