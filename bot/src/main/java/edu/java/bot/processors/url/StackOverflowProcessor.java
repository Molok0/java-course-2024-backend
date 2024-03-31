package edu.java.bot.processors.url;

import edu.java.bot.processors.url.parser.UrlParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackOverflowProcessor extends UrlProcessor {
    final String nameSite = "stackoverflow.com";

    public StackOverflowProcessor() {
    }

    @Override
    public String handle(String url) {
        String text;
        log.debug("In StackOverflowProcessor");
        if (UrlParser.getWebSiteName(url).equals(nameSite)) {
            /*
             * Какая то логика
             * Добовляем ссылку в отслеживаемые
             * */
            log.debug(nameSite);
            text = "StackOverflowProcessor" + url;
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
