package com.ximeo.smartstring.initiator;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;


public class Initiator {

    private static final String TAG_GREETING = "hello";
    private static final String TAG_STATE_OF_AFFAIRS = "routine";
    private static final String TAG_EXCHANGE_RATE = "currency";
    private static final String TAG_DATE = "date";
    private static final String TAG_WEATHER = "weather";
    private static final String TAG_TIME = "time";
    private static final String TAG_PARTING = "bye";

    private static final String COMMAND_SAY_WEATHER = "weather";
    private static final String COMMAND_SAY_GREETING = "hello";
    private static final String COMMAND_SAY_HOW_YOU_ARE = "routine";
    private static final String COMMAND_SAY_CURRENCY = "currency";
    private static final String COMMAND_SAY_CURRENT_TIME = "time";
    private static final String COMMAND_SAY_CURRENT_DATE = "date";
    private static final String COMMAND_SAY_GOODBYE = "bye";

    private Random random = new Random();

    public static void main(String[] args) {
        Initiator actions = new Initiator();
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                String question = "";
                boolean isTheLastWordInTheQuestion = false;
                while (!isTheLastWordInTheQuestion) {
                    if (!question.isEmpty()) question += " ";
                    question += scanner.next();
                    if (question.endsWith(".") || question.endsWith("?") || question.endsWith("!")) isTheLastWordInTheQuestion = true;
                }
                actions.commandDispatcher(actions.getCommand(question));
            } catch (Exception ex) {
                System.out.println("Извините, произошла непредвиденная ситуация");
                ex.printStackTrace();
            }
        }
    }

    private void commandDispatcher(String commandName) {
        switch (commandName) {
            case COMMAND_SAY_GREETING: sayHello();
                break;
            case COMMAND_SAY_HOW_YOU_ARE: sayHowAreYou();
                break;
            case COMMAND_SAY_WEATHER: sayTheWeather();
                break;
            case COMMAND_SAY_CURRENCY: sayCurrency();
                break;
            case COMMAND_SAY_CURRENT_TIME: sayTime();
                break;
            case COMMAND_SAY_CURRENT_DATE: sayDate();
                break;
            case COMMAND_SAY_GOODBYE: sayBye();
                break;
            default: sayIDontKnow();
        }
    }

    private void sayHello() {
        Set<String> answ = getAnswers().get("Приветствие");
        int numb = random.nextInt(answ.size());
        List<String> sdf = new ArrayList<>(answ);
        System.out.println(sdf.get(numb));
    }

    private void sayHowAreYou() {
        Set<String> answ = getAnswers().get("Состояние_дел");
        int numb = random.nextInt(answ.size());
        List<String> sdf = new ArrayList<>(answ);
        System.out.println(sdf.get(numb));
    }

    private void sayTheWeather() {
        Set<String> answ = getAnswers().get("Погода");
        int numb = random.nextInt(answ.size());
        List<String> sdf = new ArrayList<>(answ);
        System.out.println(sdf.get(numb));
    }

    private void sayCurrency() {
        int numb = random.nextInt(2000);
        BigDecimal bd = new BigDecimal(numb);
        BigDecimal res = (bd.divide(new BigDecimal(1000))).add(new BigDecimal(22));
        System.out.println("На данный момент курс доллара: " + res + "$");
    }

    private void sayTime() {
        System.out.println(LocalTime.now());
    }

    private void sayDate() {
        System.out.print(LocalDate.now());
    }

    private void sayIDontKnow() {
        Set<String> answ = getAnswers().get("Неизвестное");
        int numb = random.nextInt(answ.size());
        List<String> sdf = new ArrayList<>(answ);
        System.out.println(sdf.get(numb));
    }

    private void sayBye() {
        Set<String> answ = getAnswers().get("Прощание");
        int numb = random.nextInt(answ.size());
        List<String> sdf = new ArrayList<>(answ);
        System.out.println(sdf.get(numb));
        System.exit(0);
    }

    private Set<String> getTags() {
        Set<String> toSend = new HashSet<>();
        toSend.add(TOKEN_HELLO_1);
        toSend.add(TOKEN_HELLO_2);
        toSend.add(TOKEN_HELLO_3);
        toSend.add(TOKEN_WEATHER_1);
        toSend.add(TOKEN_WEATHER_2);
        toSend.add(TOKEN_HOW_ARE_YOU_1);
        toSend.add(TOKEN_HOW_ARE_YOU_2);
        toSend.add(TOKEN_HOW_ARE_YOU_3);
        toSend.add(TOKEN_DATE_1);
        toSend.add(TOKEN_DATE_2);
        toSend.add(TOKEN_BYE_1);
        toSend.add(TOKEN_BYE_2);
        toSend.add(TOKEN_BYE_3);
        toSend.add(TOKEN_CURRENCY_1);
        toSend.add(TOKEN_CURRENCY_2);
        toSend.add(TOKEN_TIME_1);
        toSend.add(TOKEN_TIME_2);
        return toSend;
    }

    private static final String TOKEN_HELLO_1 = "прив";
    private static final String TOKEN_HELLO_2 = "добр";
    private static final String TOKEN_HELLO_3 = "здравст";
    private static final String TOKEN_WEATHER_1 = "погод";
    private static final String TOKEN_WEATHER_2 = "темпер";
    private static final String TOKEN_HOW_ARE_YOU_1 = "дел";
    private static final String TOKEN_HOW_ARE_YOU_2 = "как ты";
    private static final String TOKEN_HOW_ARE_YOU_3 = "как он";
    private static final String TOKEN_DATE_1 = "дат";
    private static final String TOKEN_DATE_2 = "числ";
    private static final String TOKEN_BYE_1 = "пок";
    private static final String TOKEN_BYE_2 = "проща";
    private static final String TOKEN_BYE_3 = "до свид";
    private static final String TOKEN_CURRENCY_1 = "курс";
    private static final String TOKEN_CURRENCY_2 = "дол";
    private static final String TOKEN_TIME_1 = "врем";
    private static final String TOKEN_TIME_2 = "час";

    private String getCommand(String ssss) {
        Set<String> strs = getTagsSet(ssss);
        Map<Set<String>, String> mapa = getCommandMap();
        Set<Map.Entry<Set<String>, String>> st = mapa.entrySet();
        Map<Set<String>, String> ss = new HashMap<>();
        for (Map.Entry<Set<String>, String> s : st) {
            Set<String> dff = s.getKey();
            Set<String> localSet = new HashSet<>();
            for (String sf : dff) {
                if (strs.contains(sf)) localSet.add(sf);
            }
            if (localSet.size() == dff.size()) return s.getValue();
            ss.put(localSet, s.getValue());
        }
        int maxSuits = 0;
        String toSend = null;
        Set<Map.Entry<Set<String>, String>> sfgs = ss.entrySet();
        for (Map.Entry<Set<String>, String> sfg : sfgs) {
            if (sfg.getKey().size() > maxSuits) {
                maxSuits = sfg.getKey().size();
                toSend = sfg.getValue();
            }
        }
        return toSend;
    }

    private Set<String> getTagsSet(String str) {
        Map<Set<String>, String> sdf = getTagsMap();
        Set<Map.Entry<Set<String>, String>> cbcb = sdf.entrySet();
        Set<String> toSend = new HashSet<>();
        for (Map.Entry<Set<String>, String> ab : cbcb) {
            Set<String> s1 = ab.getKey();
            for (String ss : s1) {
                if (str.toLowerCase().contains(ss)) {
                    toSend.add(ab.getValue());
                }
            }
        }
        return toSend;
    }

    private Map<Set<String>, String> getCommandMap() {
        Map<Set<String>, String> commandMap = new HashMap<>();
        commandMap.put(Set.of(TAG_GREETING), COMMAND_SAY_GREETING);
        commandMap.put(Set.of(TAG_STATE_OF_AFFAIRS), COMMAND_SAY_HOW_YOU_ARE);
        commandMap.put(Set.of(TAG_EXCHANGE_RATE), COMMAND_SAY_CURRENCY);
        commandMap.put(Set.of(TAG_DATE), COMMAND_SAY_CURRENT_DATE);
        commandMap.put(Set.of(TAG_WEATHER), COMMAND_SAY_WEATHER);
        commandMap.put(Set.of(TAG_TIME), COMMAND_SAY_CURRENT_TIME);
        commandMap.put(Set.of(TAG_PARTING), COMMAND_SAY_GOODBYE);
        return commandMap;
    }

    private Map<Set<String>, String> getTagsMap() {
        Map<Set<String>, String> tagsMap = new HashMap<>();
        tagsMap.put(Set.of(TOKEN_HELLO_1, TOKEN_HELLO_2, TOKEN_HELLO_3), TAG_GREETING);
        tagsMap.put(Set.of(TOKEN_HOW_ARE_YOU_1, TOKEN_HOW_ARE_YOU_2, TOKEN_HOW_ARE_YOU_3),
            TAG_STATE_OF_AFFAIRS);
        tagsMap.put(Set.of(TOKEN_CURRENCY_1, TOKEN_CURRENCY_2), TAG_EXCHANGE_RATE);
        tagsMap.put(Set.of(TOKEN_DATE_1, TOKEN_DATE_2), TAG_DATE);
        tagsMap.put(Set.of(TOKEN_WEATHER_1, TOKEN_WEATHER_2), TAG_WEATHER);
        tagsMap.put(Set.of(TOKEN_TIME_1, TOKEN_TIME_2), TAG_TIME);
        tagsMap.put(Set.of(TOKEN_BYE_1, TOKEN_BYE_2, TOKEN_BYE_3), TAG_PARTING);
        return tagsMap;
    }

    private Map<String, Set<String>> getAnswers() {
        Map<String, Set<String>> answersMap = new HashMap<>();
        Set<String> howAreYouSet = new HashSet<>();
        howAreYouSet.add("Хорошо!");
        howAreYouSet.add("Спасибо, хорошо!");
        howAreYouSet.add("Бывало и лучше!");
        howAreYouSet.add("Также как и вчера!");
        howAreYouSet.add("Ничего");
        answersMap.put("Состояние_дел", howAreYouSet);
        Set<String> helloSet = new HashSet<>();
        helloSet.add("Привет!");
        helloSet.add("Здравствуйте!");
        helloSet.add("Доброго времени суток!");
        answersMap.put("Приветствие", helloSet);
        Set<String> weather = new HashSet<>();
        weather.add("Тепло");
        weather.add("Жарко");
        weather.add("Сегодня, прохладно");
        weather.add("Прохладно");
        weather.add("Солнечно");
        weather.add("Холодно");
        answersMap.put("Погода", weather);
        Set<String> parting = new HashSet<>();
        parting.add("Пока!");
        parting.add("Прощай!");
        parting.add("Аривидерчи!");
        parting.add("Чао!");
        parting.add("До встречи!");
        parting.add("Гудбай!");
        parting.add("До свидания!");
        answersMap.put("Прощание", parting);
        Set<String> unknown = new HashSet<>();
        unknown.add("Я этого пока не знаю!");
        unknown.add("Спросите, что-нибудь другое!");
        unknown.add("Кто знает!");
        answersMap.put("Неизвестное", unknown);
        return answersMap;
    }
}

