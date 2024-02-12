package edu.java.bot.processors.url;

import edu.java.bot.processors.url.parser.UrlParser;
import java.net.URI;
import java.util.logging.Logger;

public class StackOverflowProcessor extends UrlProcessor {
    private UrlProcessor next;
    private String nameSite = "stackoverflow.com";

    public StackOverflowProcessor() {
        super();
    }

    @Override
    public void handle(URI uri) {
        if (UrlParser.getWebSiteName(uri) == nameSite) {
            /*
             * Какая то логика
             * Добовляем ссылку в отслеживаемые
             * */
            System.out.println("StackOverflowProcessor" + uri.toString());
        } else if (next != null) {
            /*
             * Какая то логика
             * Передаём следующему обработчику
             * */
            next.handle(uri);
        } else {
            System.out.println("Такой сайт не может отслеживаться");
        }
        System.out.println("StackOverflowProcessor" + uri.toString());
    }
}
