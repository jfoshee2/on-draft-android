package com.ondraft.beer.brewery;

public class StateNameNumberException extends Exception {

    public String message() {
        return "State should not have numbers";
    }
}
