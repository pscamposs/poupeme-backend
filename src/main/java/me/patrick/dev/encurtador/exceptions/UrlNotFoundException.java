package me.patrick.dev.encurtador.exceptions;

public class UrlNotFoundException extends RuntimeException {
    
    public UrlNotFoundException() {
        super("Url não encontrada, tente novamente.");
    };

}
