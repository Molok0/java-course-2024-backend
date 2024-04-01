package edu.java.bot.processors.url;

import edu.java.bot.processors.url.parser.UrlParser;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GitHubProcessor extends UrlProcessor {
    public GitHubProcessor(UrlProcessor next) {
        super(next);
    }

    @Override
    public String getNameSite() {
        return "github.com";
    }

}
