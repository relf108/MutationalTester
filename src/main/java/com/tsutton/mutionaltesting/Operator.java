package com.tsutton.mutionaltesting;

import java.util.ArrayList;

public class Operator<T> {
    // val and counterpart are both counterparts to oneanother
    public String val;
    public T counterparts;

    public Operator(String val, T counterparts) {
        this.val = val;
        this.counterparts = counterparts;

    }

    public String getOperator() {
        return val;
    }

    public ArrayList<String> getCounterParts() {
        return (ArrayList<String>) counterparts;
    }

    // Checks a string for an operator, if one exists it returns its counterpart,
    // otherwise returns null
    public <T> T checkForOperator(String s) {
        if (s.toLowerCase().trim().contains(val)) {
            return (T) counterparts;
        } else if (checkAllCounterParts(s)) {
            return (T) val;
        }
        return null;
    }

    // Checks a string against all counterparts on this operator.
    private boolean checkAllCounterParts(String s) {
        ArrayList<String> counterpartsA;
        String counterpartsS;
        if(counterparts.getClass() == ArrayList.class){
            counterpartsA = (ArrayList<String>) counterparts;
            for (String s1 : counterpartsA) {
                if (s.toLowerCase().trim().contains(s1)) {
                    return true;
                }
            }
        }
        else{
            counterpartsS = (String) counterparts;
            if(s.toLowerCase().trim().contains(counterpartsS)){
                return true;
            }
        }
        return false;
    }
}
