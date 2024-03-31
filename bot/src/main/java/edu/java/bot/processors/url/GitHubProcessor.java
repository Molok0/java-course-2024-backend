package edu.java.bot.processors.url;

import edu.java.bot.processors.url.parser.UrlParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubProcessor extends UrlProcessor {
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
            log.debug("GitHubProcessor");
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

}
