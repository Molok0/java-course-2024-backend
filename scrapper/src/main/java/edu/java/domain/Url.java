package edu.java.domain;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Url {
    private Long id;
    private String url;
    private String lastCheck;

    private String lastChange;

}
