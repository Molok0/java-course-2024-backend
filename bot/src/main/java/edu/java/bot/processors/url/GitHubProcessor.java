package edu.java.bot.processors.url;

import edu.java.bot.processors.url.parser.UrlParser;
import java.net.URI;

public class GitHubProcessor extends UrlProcessor {
    private UrlProcessor next;
    private String nameSite = "github.com";

    public GitHubProcessor() {
        super();
    }

    @Override
    public void handle(URI uri) {
        if (UrlParser.getWebSiteName(uri) == nameSite) {
            /*
             * Какая то логика
             * Добовляем ссылку в отслеживаемые
             * */
            System.out.println("GitHubProcessor" + uri.toString());
        } else if (next != null) {
            /*
             * Какая то логика
             * Передаём следующему обработчику
             * */
            next.handle(uri);
        } else {
            System.out.println("Такой сайт не может отслеживаться");
        }
    }
}
