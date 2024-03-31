package edu.java.processors;

import edu.java.configuration.ClientConfig;
import edu.java.processors.parser.UrlParser;
import org.springframework.stereotype.Component;

@Component
public class StackOverflowProcessor extends UrlProcessor {
    private final String nameSite = "stackoverflow.com";
    private ClientConfig clientConfig;

    public StackOverflowProcessor(ClientConfig clientConfig) {
        this.clientConfig = clientConfig;
    }

    @Override
    public String handle(String url) {
        String text;
        if (UrlParser.getWebSiteName(url).equals(nameSite)) {
            /*
             * Какая то логика
             * Добовляем ссылку в отслеживаемые
             * */
            text = url;
        } else if (next != null) {
            /*
             * Какая то логика
             * Передаём следующему обработчику
             * */
            text = next.handle(url);
        } else {
            text = "Такой сайт не может отслеживаться StackOverflowProcessor";
        }
        return text;
    }

}
