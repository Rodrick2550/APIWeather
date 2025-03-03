package com.codehive.apiclima.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CodeWeatherMap {

    private final Map<Integer, String > codeWeatherMap;


    public CodeWeatherMap(){
        codeWeatherMap = new HashMap<>();
        weatherCodes();

    }

    private void weatherCodes(){
        codeWeatherMap.put(0, "Cielo despejado");

        codeWeatherMap.put(1, "Principalmente despejado, parcialmente nublado y cubierto.");
        codeWeatherMap.put(2, "Principalmente despejado, parcialmente nublado y cubierto.");
        codeWeatherMap.put(3, "Principalmente despejado, parcialmente nublado y cubierto.");

        codeWeatherMap.put(45, "Niebla y depósito de niebla de escarcha");
        codeWeatherMap.put(48, "Niebla y depósito de niebla de escarcha");

        codeWeatherMap.put(51, "Llovizna: Intensidad ligera, moderada y densa");
        codeWeatherMap.put(53, "Llovizna: Intensidad ligera, moderada y densa");
        codeWeatherMap.put(55, "Llovizna: Intensidad ligera, moderada y densa");

        codeWeatherMap.put(56, "Llovizna helada: intensidad ligera y densa");
        codeWeatherMap.put(57, "Llovizna helada: intensidad ligera y densa");

        codeWeatherMap.put(61, "Lluvia: Intensidad ligera, moderada y fuerte");
        codeWeatherMap.put(63, "Lluvia: Intensidad ligera, moderada y fuerte");
        codeWeatherMap.put(65, "Lluvia: Intensidad ligera, moderada y fuerte");

        codeWeatherMap.put(66, "Lluvia helada: intensidad ligera y fuerte");
        codeWeatherMap.put(67, "Lluvia helada: intensidad ligera y fuerte");

        codeWeatherMap.put(71, "Nevadas: intensidad ligera, moderada y fuerte");
        codeWeatherMap.put(73, "Nevadas: intensidad ligera, moderada y fuerte");
        codeWeatherMap.put(75, "Nevadas: intensidad ligera, moderada y fuerte");

        codeWeatherMap.put(77, "Granos de nieve");

        codeWeatherMap.put(80, "Lluvias: ligeras, moderadas y violentas");
        codeWeatherMap.put(81, "Lluvias: ligeras, moderadas y violentas");
        codeWeatherMap.put(82, "Lluvias: ligeras, moderadas y violentas");

        codeWeatherMap.put(85, "Chubascos de nieve ligeros y fuertes");
        codeWeatherMap.put(86, "Chubascos de nieve ligeros y fuertes");

        codeWeatherMap.put(95, "Tormenta eléctrica: leve o moderada");

        codeWeatherMap.put(96, "Tormenta eléctrica con granizo ligero y fuerte");
        codeWeatherMap.put(99, "Tormenta eléctrica con granizo ligero y fuerte");
    }
    public String getWeatherDescription(int weatherCode) {
        return codeWeatherMap.getOrDefault(weatherCode, "Código de clima desconocido");
    }
}
