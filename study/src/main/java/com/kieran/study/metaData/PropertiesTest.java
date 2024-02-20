package com.kieran.study.metaData;

import com.kieran.study.dto.CarDTO;
import com.kieran.study.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.comparator.Comparators;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertiesTest {

    public static void main(String[] args) {
        List<CarDTO> list = new ArrayList<>();

        CarDTO u2 = new CarDTO("Askskkskskskss", "blue", false, 150L);
        CarDTO u3 = new CarDTO("Askskksksksksf", "red", true, 100L);
        CarDTO u4 = new CarDTO("Askskkskskskss", "grown", true, 200L);
        CarDTO u1 = new CarDTO("Askskkskskskss", "oo", true, 105L);

        list.add(u1);
        list.add(u4);
        list.add(u2);
        list.add(u3);



        Map<String, CarDTO> dMap = list.stream()
                .collect(Collectors.toMap(CarDTO::getBrand, Function.identity(),
                        (o1, o2) -> o1));
        List<CarDTO> search = new ArrayList<>(dMap.values()).stream().sorted(Comparator.comparing(CarDTO::getBrand).reversed()).collect(Collectors.toList());

        for (CarDTO c : search) {
            System.err.println(c.toString());
        }


    }



}
