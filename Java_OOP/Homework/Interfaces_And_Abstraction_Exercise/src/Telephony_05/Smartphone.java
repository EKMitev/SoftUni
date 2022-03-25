package Telephony;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {

    private List<String> numbers;
    private List<String> urls;
    private Pattern pattern;
    private Matcher matcher;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String url : urls) {

            this.pattern = Pattern.compile("\\d");
            this.matcher = pattern.matcher(url);
            if (matcher.find()) {
                stringBuilder.append("Invalid URL!").append(System.lineSeparator());
            } else {
                stringBuilder.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String number : numbers) {

            this.pattern = Pattern.compile("\\b\\d+\\b");
            this.matcher = pattern.matcher(number);

            if (matcher.find()) {
                stringBuilder.append("Calling... ").append(number).append(System.lineSeparator());
            } else {
                stringBuilder.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return stringBuilder.toString().trim();
    }
}
