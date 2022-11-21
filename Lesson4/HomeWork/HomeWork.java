package Lesson4.HomeWork;

import Lesson4.HomeWork.expert.GeneratorExpertHomework;

import java.util.*;
import java.util.regex.Pattern;

public class HomeWork {
    public static void main(String[] args) {
        // Экспертный уровень:
        // Предыстория: Мы находимся в статистическом институте. Хочется понять уровень миграции между регионами за месяц.
        // Для этого было решено произвести анализ передвижения автомобилей: на границе каждого региона стоят камеры,
        // фиксирующие въезд и выезд автомобилей. Формат автомобильного номера: (буква)(3 цифры)(2 буквы)(2-3 цифры)
        // К474СЕ178 - где 178 регион

        // Задача №1: узнать сколько всего машин со спец номерами(вывести на экран): начинаются на M
        // и заканчиваются на АВ (русские буквы).
        // Не повторяющиеся
        //Для генерации данных воспользоваться GeneratorExpertHomework.getData()

        //Входящие данные
        // Map<Integer, Map<String, String[]>> - где ключ первого уровня - номер региона,
        // out, input - ключи второго уровня (выезд, въезд), и String[] - массивы номеров.
        // { 1 : {
        //      "out" : ["К474СЕ178"],
        //      "input": ["А222РТ178"]
        //    },
        //   2 : {
        //        "out" : ["К722АВ12", "А222РТ178"],
        //        "input" : ["М001АВ01", "А023РВ73"],
        //   }
        // ..
        //  }

        //Список технологий:
        // Set (HashSet) - узнать что это, set.contains(), set.put()
        // Map (HashMap) - узнать что это, map.get(), map.put(), map.entrySet() - для итерации, entry.getValue(), entry.getKey()
        // <Integer> - обозначает тип который хранится в этой структуре данных (Generics)
        // Регулярные выражения - вытащить регион авто

        Map<Integer, Map<String, String[]>> data = GeneratorExpertHomework.getData();

        Pattern compile = Pattern.compile("М\\d{3}АВ\\d{2,3}");

        HashSet<String> cb= new HashSet<>();

        for(Map<String, String[]> h : data.values()) {
            for(String[] s : h.values()) {
                for(String m : s){
                    if (compile.matcher(m).find()) {
                        cb.add(m);
                    }

                }
            }
        }

        System.out.println(cb);

    }
}
