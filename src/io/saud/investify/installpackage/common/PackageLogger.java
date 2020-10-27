package io.saud.investify.installpackage.common;

/**
 * @author SAUD
 * @created 27/10/2020 - 11:33 AM
 */
public class PackageLogger {


    public static void info(String message){
        System.out.print(ConsoleColors.RESET);
        System.out.print(ConsoleColors.CYAN);
        System.out.println(message);
        System.out.print(ConsoleColors.RESET);
    }


    public static void err(String message){
        System.out.print(ConsoleColors.RED);
        System.out.print(ConsoleColors.CYAN);
        System.out.println(message);
        System.out.print(ConsoleColors.RESET);
    }
}
