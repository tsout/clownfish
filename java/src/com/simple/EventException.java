package com.simple;

public class EventException extends Exception {

    private String errorMsg;
    /**
     * 
     */
    private static final long serialVersionUID = 8887776359897572232L;


    EventException(final String msg) {
        if (msg != null) {
            errorMsg = new String(msg);
        }
        System.err.println("[Event Exception] " + msg);

    }


    @Override
    public String toString() {
        return "EventException [errorMsg=" + errorMsg + "]";
    }

}
