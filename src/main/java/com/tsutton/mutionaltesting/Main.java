package com.tsutton.mutionaltesting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        File classFile = new File("/home/tsutton/Projects/MutationalTesting/mutationalTesting/class.txt");
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(classFile));

            while (reader.ready()) {
                String l = reader.readLine();
                ArrayList<String> operators = detectOperators(l);
                for (String o : operators) {
                    File mutant = new File("/home/tsutton/Documents/mutant" + o);
                    BufferedWriter writer = new BufferedWriter(new FileWriter(mutant));
                    writer.write(splitLine(l));
                    writer.close();
                }
                ;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("IOException: File not found");
            e.printStackTrace();
        }

    }

    private static ArrayList<String> detectOperators(String l) {
        ArrayList<String> newOperators = new ArrayList<>();
        for (Operator o : MutationalOperators.getOperatorList()) {
            if (o.checkForOperator(l) != null) {
                newOperators.add((String) o.checkForOperator(l));
            }
        }
        return newOperators;
    }

    private static String splitLine(String l) {
        for (Operator o : MutationalOperators.getOperatorList()) {
            if (l.contains(o.val)) {
                // String[] lArray = l.split(o.val);
                String[] splitString = l.split(o.val);
                String[] lArray = new String[splitString.length + 2];
                lArray[0] = splitString[0];
                lArray[1] = o.counterparts.toString();
                lArray[2] = splitString[1];
                String ln = new String();
                for (String s : lArray) {
                    if (s != null) {
                        ln += s;
                    }
                }
                return ln;
            } else if (l.contains(o.counterparts.toString())) {
                String[] splitString = l.split(Pattern.quote((String) o.counterparts));
                String[] lArray = new String[splitString.length + 2];
                lArray[0] = splitString[0];
                lArray[1] = o.val;
                lArray[2] = splitString[1];
                String ln = new String();
                for (String s : lArray) {
                    if (s != null) {
                        ln += s;
                    }
                }
                return ln;
            }
        }
        return l;
    }
}
