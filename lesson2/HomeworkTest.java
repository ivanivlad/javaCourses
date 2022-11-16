package lesson2;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkTest {

    @org.junit.jupiter.api.Test
    void maskHtml() {
        String htmlText = "<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client>";
        // "<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>";

        String htmlText2 = "<client>(Какие то данные)<data></data></client>";
        //<client>(Какие то данные)<data></data></client> - вернет тоже (никаких ошибок)

        String htmlText3 = "<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344</data></client>";
        //"<client>(Какие то данные)<data>И****в Иван И.;7999***3344</data></client>"

        String test1 = "ff<data>frfr</data>dd";

        System.out.println("======TEST 1 EXECUTED=======");

        assertEquals("<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>",
                Homework.MaskHtml(htmlText));
        System.out.println("======TEST 2 EXECUTED=======");
        assertEquals("<client>(Какие то данные)<data></data></client>",
                Homework.MaskHtml(htmlText2));
        System.out.println("======TEST 3 EXECUTED=======");
        assertEquals("<client>(Какие то данные)<data>И****в Иван И.;7999***3344</data></client>",
                Homework.MaskHtml(htmlText3));


        try{
            Homework.MaskHtml(test1);
        } catch (IllegalArgumentException thrown)
        {
            System.out.println("======TEST 3 EXECUTED=======");
            assertNotEquals("", thrown.getMessage());

        }
    }
}