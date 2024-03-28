package edu.java.bot.services;

import edu.java.bot.services.WebSiteProcessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WebSiteProcessorServiceTest {
    @InjectMocks
    private WebSiteProcessorService webSiteProcessorService;

    @Test
    void getUrlProcessorTest(){
        Assertions.assertEquals(webSiteProcessorService.getUrlProcessor().getNameSite(), "github.com");
    }
    @Test
    void getNextUrlProcessorTest(){
        Assertions.assertEquals(webSiteProcessorService.getUrlProcessor().getNext().getNameSite(), "stackoverflow.com");
    }
}
