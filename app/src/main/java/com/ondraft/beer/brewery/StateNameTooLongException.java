package com.ondraft.beer.brewery;

public class StateNameTooLongException extends Exception {

    public String message() {
        return "State name too long";
    }
}
