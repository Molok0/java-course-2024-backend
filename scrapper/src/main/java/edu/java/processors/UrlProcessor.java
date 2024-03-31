package edu.java.processors;

public abstract class UrlProcessor {

    protected UrlProcessor next;
    protected String nameSite;

    public abstract String handle(String url);

    public String getNameSite() {
        return nameSite;
    }

    public UrlProcessor getNext() {
        return next;
    }

    public void setNext(UrlProcessor next) {
        this.next = next;
    }
}
