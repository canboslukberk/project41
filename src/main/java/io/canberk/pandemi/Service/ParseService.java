package io.canberk.pandemi.Service;

import io.canberk.pandemi.Entity.Stat;
import io.canberk.pandemi.Entity.StatUpdatePayload;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseService {

    private final StatUpdatePayload news;

    public ParseService(StatUpdatePayload news) {
        this.news = news;
    }

    public Stat performParse() {
        String text = this.news.getNews();
        String city = "null";
        int cases = 0;
        int deaths = 0;
        int recovered = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        String[] splitted = text.split("\n|\\.(?!\\d)|(?<!\\d)\\.");
        for (String s : splitted) {
            Date newDate = checkDate(s);
            if (newDate != null) {
                date = newDate;
                s = s.replace(formatter.format(date), "");
            }
            String newCity = checkCity(s);
            int newDeath = checkDeaths(s);
            int newCase = checkCase(s);
            int newRecovered = checkrecovered(s);

            if (newCity != null) {
                city = newCity;
            }
            if (newCase > 0) {
                cases = newCase;
            }
            if (newDeath > 0) {
                deaths = newDeath;
            }
            if (newRecovered > 0) {
                recovered = newRecovered;
            }
        }
        if(city.equals("null") || cases == 0 || deaths == 0 || recovered == 0){
            return null;
        }else {
            return new Stat(date, city, cases, deaths, recovered);
        }
    }

    private int checkrecovered(String text){
        String RECOVERD_STRING = "taburcu";
        if(text.toLowerCase().contains(RECOVERD_STRING)){
            return Integer.parseInt(text.replaceAll("[\\D]", ""));
        }
        return -1;
    }

    private int checkDeaths(String text){
        String DEATH_STRING = "vefat";
        if(text.toLowerCase().contains(DEATH_STRING)){
            return Integer.parseInt(text.replaceAll("[\\D]", ""));
        }
        return -1;
    }

    private int checkCase(String text){
        String CASE_STRING = "vaka";
        if(text.toLowerCase().contains(CASE_STRING)){
            return Integer.parseInt(text.replaceAll("[\\D]", ""));
        }
        return -1;
    }


    private String checkCity(String text) {
        for (String city : getCityList()) {
            if (text.toLowerCase().contains(city.toLowerCase())) {
                return city.toLowerCase().trim();
            }
        }
        return null;
    }


    private Date checkDate(String text) {
        try {
            String regex = "\\d{2}.\\d{2}.\\d{4}";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(text);
            if (!m.find())
                return null;
            String dateStr = m.group(0);
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            df.setLenient(false);


            return df.parse(dateStr);
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
            return null;
        }
    }

    private List<String> getCityList() {
        return Arrays.asList(
                "Adana",
                "Adıyaman",
                "Afyonkarahisar",
                "Aksaray",
                "Amasya",
                "Ankara",
                "Antalya",
                "Ardahan",
                "Artvin",
                "Aydın",
                "Ağrı",
                "Balıkesir",
                "Bartın",
                "Batman",
                "Bayburt",
                "Bilecik",
                "Bingöl",
                "Bitlis",
                "Bolu",
                "Burdur",
                "Bursa",
                "Denizli",
                "Diyarbakır",
                "Düzce",
                "Edirne",
                "Elazığ",
                "Erzincan",
                "Erzurum",
                "Eskişehir",
                "Gaziantep",
                "Giresun",
                "Gümüşhane",
                "Hakkâri",
                "Hatay",
                "Isparta",
                "Iğdır",
                "İstanbul",
                "İzmir",
                "Kahramanmaraş",
                "Karabük",
                "Karaman",
                "Kars",
                "Kastamonu",
                "Kayseri",
                "Kilis",
                "Kocaeli",
                "Konya",
                "Kütahya",
                "Kırklareli",
                "Kırıkkale",
                "Kırşehir",
                "Malatya",
                "Manisa",
                "Mardin",
                "Mersin",
                "Muğla",
                "Muş",
                "Nevşehir",
                "Niğde",
                "Ordu",
                "Osmaniye",
                "Rize",
                "Sakarya",
                "Samsun",
                "Siirt",
                "Sinop",
                "Sivas",
                "Tekirdağ",
                "Tokat",
                "Trabzon",
                "Tunceli",
                "Uşak",
                "Van",
                "Yalova",
                "Yozgat",
                "Zonguldak",
                "Çanakkale",
                "Çankırı",
                "Çorum",
                "Şanlıurfa",
                "Şırnak");
    }
}
