package com.tsutton.mutionaltesting;

import java.util.ArrayList;

public class MutationalOperators {

    private static ArrayList<Operator> operatorList = new ArrayList<Operator>();

    public static ArrayList<Operator> getOperatorList() {
        operatorList.add(new Operator<String>(">", ">="));
        operatorList.add(new Operator<String>("<", "<="));
        operatorList.add(new Operator<String>("-", "+"));

        ArrayList<String> mulitplyMutators = new ArrayList<String>();
        mulitplyMutators.add("/");
        mulitplyMutators.add("%");
        operatorList.add(new Operator<ArrayList<String>>("*", mulitplyMutators));

        ArrayList<String> andMutators = new ArrayList<String>();
        andMutators.add("|");
        andMutators.add("^");
        operatorList.add(new Operator<ArrayList<String>>("&", andMutators));

        ArrayList<String> doubleLessThan = new ArrayList<String>();
        doubleLessThan.add(">>");
        doubleLessThan.add(">>>");
        operatorList.add(new Operator<ArrayList<String>>("<<", doubleLessThan));
        
        return operatorList;
    }
}