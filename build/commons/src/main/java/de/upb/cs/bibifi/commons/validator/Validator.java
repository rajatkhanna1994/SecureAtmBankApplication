package de.upb.cs.bibifi.commons.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Validator {

    public Validator() {
    }

    /**
     * check if arguments are duplicated
     *
     * @param option
     * @param options
     * @return
     */
    public boolean checkDuplicates(String option, Set<String> options) {
        return options.add(option);
    }

    /**
     * Check regex validation for numeric value
     *
     * @param numeral
     * @return
     */
    public boolean validateNumerals(String numeral) {
        double max_amount = 4294967295.99;
        double amount = 0;
        Pattern pattern = Pattern.compile("^(0|[1-9][0-9]*)\\.\\d{2}|([1-9][0-9]*)$");
        try {
            amount = Double.parseDouble(numeral);
        } catch (NumberFormatException e) {

        }
        return numeral.matches(pattern.pattern()) ? amount <= max_amount : false;
    }

    /**
     * Check regex validation for IP address
     *
     * @param ipString
     * @return
     */
    public boolean validateIP(String ipString) {
        Pattern pattern = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
        return pattern.matcher(ipString).matches();
    }

    /**
     * Check regex validation for port
     *
     * @param portString
     * @return
     */
    public boolean validatePort(String portString) {
        int min_num = 1024;
        int max_num = 65535;
        int amount = 0;
        try {
            amount = Integer.parseInt(portString);
        } catch (NumberFormatException e) {

        }
        return amount >= min_num && amount <= max_num ? true : false ;
    }

    /**
     * Check regex validation for File names
     *
     * @param fileName
     * @return
     */
    public boolean validateFileName(String fileName) {
        Pattern pattern = Pattern.compile("[_\\-\\.0-9a-z]+$");
        return (fileName.length() <= 127 && !fileName.equals(".")  && !fileName.equals("..") )  ? pattern.matcher(fileName).matches() : false;
    }

    /**
     * Check regex validation for File names
     *
     * @param accountName
     * @return
     */
    public boolean validateAccountName(String accountName) {
        Pattern pattern = Pattern.compile("[_\\-\\.0-9a-z]+$");
        return accountName.length() <= 122 ? pattern.matcher(accountName).matches() : false;
    }

    /**
     * Check regex validation for File names
     *
     * @param initialBalance
     * @return
     */
    public boolean validateInitialBalance(String initialBalance) {
        double amount = 0;
        try {
            amount = Double.parseDouble(initialBalance);
        } catch (NumberFormatException e) {

        }
        return amount >= 10.0 ? true : false;
    }

    /**
     * check if arguments contain more than one Operations
     *
     * @param options
     * @return
     */
    public boolean checkOperations(Set<String> options) {

        final String CMD_D = "d";
        final String CMD_W = "w";
        final String CMD_N = "n";
        final String CMD_G = "g";

        Set<String> operations = new HashSet<>();
        operations.add(CMD_D);
        operations.add(CMD_W);
        operations.add(CMD_N);
        operations.add(CMD_G);

        boolean match = false;
        for (String str : operations) {
            if (options.contains(str) && match != true) {
                match = true;
            }else if (options.contains(str) && match == true){
                match = false;
                break;
            }
        }
        return match;
    }

    /**
     * Check regex validation for File names
     *
     * @param args
     * @return
     */
    public boolean validateArgumentLength( String[] args) {
        return  !Arrays.stream(args).anyMatch(arg->arg.length()>4096);
    }
}
