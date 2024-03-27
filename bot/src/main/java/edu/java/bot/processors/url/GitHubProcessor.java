package edu.java.bot.processors.url;

import edu.java.bot.processors.url.parser.UrlParser;
import org.springframework.stereotype.Component;

@Component
public class GitHubProcessor extends UrlProcessor {
    private UrlProcessor next;

    private final String nameSite = "github.com";

    public GitHubProcessor() {

    }

    @Override
    public String handle(String url) {
        String text;
        if (UrlParser.getWebSiteName(url).equals(nameSite)) {
            /*
             * Какая то логика
             * Добовляем ссылку в отслеживаемые
             * */
            text = "GitHubProcessor\t" + url;
        } else if (next != null) {
            /*
             * Какая то логика
             * Передаём следующему обработчику
             * */
            text = next.handle(url);
        } else {
            text = "Такой сайт не может отслеживаться";
        }
        return text;
    }

    @Override
    public String getNameSite() {
        return nameSite;
    }
}
