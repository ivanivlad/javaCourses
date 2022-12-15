package HomeWork5;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        /*try{
            DataGenerator.createReports("");
        } catch (IOException e)
        { System.out.println(e.getMessage()); }*/

        try{
            File[] files= My.getFiles();
            System.out.println("----------Задача 1------------");
            My.reportProfitByMarket("pyterochka", files);
            System.out.println("----------Задача 2-------------");
            My.reportOutcomeByPeriod( files);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static class My{
        public static File[] getFiles() {

            URL resource = My.class.getClassLoader().getResource("resources/Reports");
            if (resource == null) {
                throw new RuntimeException("Создай папку с файлами");
            }
            File dir = new File(resource.getFile());
            File[] files = dir.listFiles();
            if (files == null) {
                throw new RuntimeException("В папке нет файлов");
            }
            return files;
        }

        public static void reportOutcomeByPeriod(File[] files ) throws IOException {

            Map<String, BigDecimal> marketOutcome = new HashMap<>();

            for (File nFile : files) {
                String pathFile = nFile.getPath();

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
                    while (bufferedReader.ready()) {
                        String s = bufferedReader.readLine();

                        String[] dataString = s.split(";");
                        if (dataString[0].equalsIgnoreCase("МАГАЗИН")) {
                            continue;
                        }
                        String market = dataString[0];
                        BigDecimal outcome = new BigDecimal(dataString[2]);

                        if (!marketOutcome.containsKey(market)) {
                            marketOutcome.put(market, new BigDecimal("0.00"));
                        }
                        marketOutcome.put(market, marketOutcome.get(market).add(outcome));

                    }

                }
            }

            marketOutcome
                   .forEach((k,v) -> System.out.println("Расходы " + k + " за весь период: " + v));
        }

        public static void reportProfitByMarket(String marketName, File[] files ) throws IOException {

            Map<String, HashMap<LocalDate, BigDecimal>> marketProfit = new HashMap<>();

            for (File nFile : files) {
                String pathFile = nFile.getPath();

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathFile))) {
                    while (bufferedReader.ready()) {
                        String s = bufferedReader.readLine();

                        String[] dataString = s.split(";");
                        if (dataString[0].equalsIgnoreCase("МАГАЗИН")) {
                            continue;
                        }
                        String market = dataString[0];
                        BigDecimal income = new BigDecimal(dataString[1]);
                        BigDecimal outcome = new BigDecimal(dataString[2]);
                        LocalDate date = LocalDate.parse(dataString[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        LocalDate month = date.withDayOfMonth(1);
                        BigDecimal profit = income.add(outcome.negate());//income - outcome;

                        if (!marketProfit.containsKey(market)) {
                            marketProfit.put(market, new HashMap<>());
                        }
                        if (!marketProfit.get(market).containsKey(month)) {
                            marketProfit.get(market).put(month, new BigDecimal("0.00"));
                        }

                        marketProfit.get(market).put(month, marketProfit.get(market).get(month).add(profit));

                    }

                }
            }

            HashMap<LocalDate, BigDecimal> pyaterochka= marketProfit.get(marketName);
            pyaterochka.keySet()
                    .stream()
                    .sorted()
                    .forEach((k) -> System.out.println(k.getMonthValue() +"." +k.getYear() +" "+ pyaterochka.get(k)));

        }
    }
}