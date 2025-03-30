package com.challenge.api.exception;

public class PostConsentsException extends RuntimeException{

    public PostConsentsException(){super("CPF inválido, precisa estar no seguinte formato ###.###.###-##");}

    public PostConsentsException(String message){super(message);}
}
