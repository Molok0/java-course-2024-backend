package edu.java.processors;


import edu.java.processors.parser.UrlParser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class UrlProcessor {
    protected UrlProcessor next;

    public UrlProcessor(UrlProcessor next) {
        this.next = next;
    }

    public UrlProcessor getNext() {
        return this.next;
    }

    public final String handle(String url) {
        String text;
        if (UrlParser.getWebSiteName(url).equals(this.getNameSite())) {
            /*
             * Какая то логика
             * */
            text = getLastChanges(url);
            log.debug(this.getNameSite());
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

    public abstract String getLastChanges(String urlString);

    public abstract String getNameSite();
}
