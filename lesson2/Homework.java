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

    public static String MaskHtml(String html){

        if(html.isEmpty())
        {
            return html;
        }

        String[] subText = html.split("<data>|</data>");

        if(subText.length !=3 || subText[1].isEmpty() )
        {
            return html;

        }

        String dataText = subText[1]; //data block

        String[] contacts = dataText.split(";");

        if(contacts.length == 0)
       {
            return html;
       }

        for (int i = 0; i < contacts.length; i++) {
            String contact = contacts[i];

            if (contact.matches("^7([0-9]{10})$")) //telephone
            {
                StringBuilder nTelephone = new StringBuilder(contact);
                nTelephone.replace(4,7, "*".repeat(3)) ;

                contact = nTelephone.toString();

            } else if (contact.matches("^[A-Za-z0-9+_.\\-]+@(.+)$")) //email
            {
                String[] emailParts = contact.split("@|(\\.)");
                contact = String.format("%s*@%s.%s",
                        emailParts[0].substring(0, emailParts[0].length() - 1),
                        "*".repeat(emailParts[1].length()),
                        emailParts[2]);

            } else if (contact.matches("^([А-Яа-я]+) ([А-Яа-я]+) ([А-Яа-я]+)?")) //FullName
            {
                String[] fullName = contact.split(" ");

                int nFullNameLength = fullName[0].length();

                fullName[0] = fullName[0].substring(0, 1)
                        .concat("*".repeat(nFullNameLength - 2))
                        .concat(fullName[0].substring(nFullNameLength - 1, nFullNameLength));

                fullName[2] = fullName[2].substring(0, 1).concat(".");

                contact = String.join(" ", fullName);
            } else {
                throw new IllegalArgumentException("there are no contacts in the data");
            }
            contacts[i] = contact;

        }

        return subText[0] + "<data>" + String.join(";", contacts) + "</data>" + subText[2];

    }

    public static void main(String[] args) {

        String htmlText = "<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client>";
        // "<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>";

        String maskedHtml = MaskHtml(htmlText);

        System.out.println(htmlText);
        System.out.println(maskedHtml);
    }
}
