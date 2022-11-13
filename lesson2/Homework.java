package lesson2;

public class Homework {

    //Экспертный уровень
    //Задача №1
    //Создать метод маскирования персональных данных, который:
    //Телефон (до/после маскирования): 79991113344 / 7999***3344
    //Email (до/после маскирования): test@yandex.ru / tes*@******.ru, my_mail@gmail.com / my_mai*@*****.com
    //Фио (до/после маскирования): Иванов Иван Иванович / И****в Иван И.
    //
    //Входящие параметры: String text
    //Возвращаемый результат: String
    //
    //Примеры возможного текста:
    //<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client> -
    // "<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>"
    //<client>(Какие то данные)<data></data></client> - вернет тоже (никаких ошибок)
    //<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344</data></client> - "<client>(Какие то данные)<data>И****в Иван И.;7999***3344</data></client>"

    //Используемые технологии: String.find, String.replaceAll, String.split, String.join, String.contains, String.substring
    //Регулярные выражения, класс StringBuilder

    public static void main(String[] args) {

        String htmlText = "<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client>";
        // "<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>";

        String htmlText2 = "<client>(Какие то данные)<data></data></client>";
        //<client>(Какие то данные)<data></data></client> - вернет тоже (никаких ошибок)

        String htmlText3 = "<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344</data></client>";
        //"<client>(Какие то данные)<data>И****в Иван И.;7999***3344</data></client>"

        //TODO
        // выделить блок data
        // разделить по ;
        // если только цифры в строке - первые 4 и последние 4
        // если @ - почта 3 первых и домен с точкой
        // если ФИО - разделить пробелами, фамилия - первая и последняя буква, Имя полностью, Отчетсво - первая буква
        //StringBuilder bufText = new StringBuilder(htmlText);

        String[] subText = htmlText.split("<data>|</data>");
        String dataText = subText[1];
        String[] contacts = dataText.split(";");
        boolean isEmail = contacts[1].matches("^[A-Za-z0-9+_.\\-]+@(.+)$");

        String[] emailParts = contacts[1].split("@");
        String[] domainParts = emailParts[1].split("\\.");
        String nEmail = emailParts[0].substring(0,emailParts[0].length())
                + "*@"
                + "*".repeat(domainParts[0].length())
                + "."
                + domainParts[1];

        boolean isTelephone = contacts[0].matches("^7([0-9]{10})$");
        //телефон
        String newT = contacts[0].substring(0,4) + "*".repeat(3) + contacts[0].substring(6,10);
        //почта - 1 символ перед собакой, символы между собакой и точкой

        boolean isFullName = contacts[2].matches("^([А-Яа-я]+)\\ ([А-Яа-я]+)(\\ ([А-Яа-я]+))?");
        String[] fullName = contacts[2].split(" ");

        String nFullName = fullName[0];

        int nFullNameLength = fullName[0].length();

        /* работаеющий вариант на regexp
        String nFullName2 = nFullName.replaceAll(".", "*")
                .replaceFirst("^.", nFullName.substring(0,1))
                .replaceFirst(".$", nFullName.substring(nFullNameLength-1,nFullNameLength));*/
        //более простой вариант
        String nFullName2 = nFullName.substring(0,1)
                + "*".repeat(nFullNameLength-2)
                + nFullName.substring(nFullNameLength-1,nFullNameLength);


        String nSecName = fullName[1].substring(0,1).concat(".");



        //String maskedText = "";
        //System.out.println(maskedText);

    }
}
