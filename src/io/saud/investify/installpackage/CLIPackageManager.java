package io.saud.investify.installpackage;


import io.saud.investify.installpackage.common.ConsoleColors;
import io.saud.investify.installpackage.common.PackageException;
import io.saud.investify.installpackage.common.PackageLogger;
import io.saud.investify.installpackage.packagemanager.PackageService;
import io.saud.investify.installpackage.packagemanager.PackageServiceImpl;

import java.util.Scanner;

/**
 * @author SAUD
 * @created 25/10/2020 - 10:39 PM
 */
public class CLIPackageManager {

    private static final int PAGE_SIZE = 35;


    private Scanner scanner;
    PackageService packageService;

    public static void main(String[] args) {
        CLIPackageManager cliPackageManager = new CLIPackageManager();

        cliPackageManager.initiateCLIApplication();
    }

    public void initiateCLIApplication() {
        scanner = new Scanner(System.in);
        initiateRepository();
        clearScreen();
        proceedWithApplication();
    }

    void proceedWithApplication() {
        do {
            header();
            String userInputCommand = readInput();
            String[] userCommand = commandParser(userInputCommand);
            switch (userCommand[0].toLowerCase()) {
                case "install":
                    installPackage(userCommand[1]);
                    break;
                case "remove":
                    removePackage(userCommand[1]);
                    break;
                case "list":
                    listOfPackages(userCommand[1]);
                    break;
                case "exit":
                    terminate();
                    break;
                default:
                    PackageLogger.err("'" + userCommand[0] + "' command not found!");
            }
            PackageLogger.info("Press any key to continue");
            readInput();
            clearScreen();
        } while (true);
    }


    void installPackage(String packageName) {
        try {
            packageService.install(packageName);
            PackageLogger.info("Package " + packageName + " has been installed");
        } catch (PackageException e) {
            PackageLogger.err(e.getMessage());
        }
    }

    void removePackage(String packageName) {
        try {
            packageService.remove(packageName);
            PackageLogger.info("Package " + packageName + " has been removed");
        } catch (PackageException e) {
            PackageLogger.err(e.getMessage());
        }
    }

    void listOfPackages(String args) {
        try {
            PackageLogger.info("List of packages \n" + packageService.list(args));
        } catch (PackageException e) {
            PackageLogger.err(e.getMessage());
        }
    }

    void initiateRepository() {
        try {
            resetConsoleColor();
            System.out.print(ConsoleColors.BLUE_BOLD);
            System.out.println("Please Enter absolute path of package text file");
            resetConsoleColor();
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Leave blank if you want to use default package.txt file");
            resetConsoleColor();
            String fileLocation = readInput();
            if (!fileLocation.isEmpty()) {
                packageService = new PackageServiceImpl(fileLocation);
            } else {
                packageService = new PackageServiceImpl("package.txt");
            }
        }catch (Exception e){
            PackageLogger.err(e.getMessage());
        }
    }

    void header() {
        System.out.print(ConsoleColors.GREEN_BOLD);
        System.out.println("Welcome to (っ◔◡◔)っ ♥ PACKAGE MANAGER ♥");
        System.out.println("Please Enter your command");
        resetConsoleColor();
        System.out.print(ConsoleColors.WHITE_BOLD);
        System.out.println("Options:");
        resetConsoleColor();
        System.out.print(ConsoleColors.YELLOW_BOLD_BRIGHT);
        System.out.println("\t install <package-name>");
        System.out.println("\t remove <package-name>");
        System.out.println("\t list [-installed | -all]");
        resetConsoleColor();
        System.out.print(ConsoleColors.BLUE_BOLD);
        System.out.println("Type exit to terminate the application");
        resetConsoleColor();
    }

    public void resetConsoleColor() {
        System.out.print(ConsoleColors.RESET);
    }

    public String readInput() {
        return scanner.nextLine();
    }


    public String[] commandParser(String userInput) {
        String[] tempCommand = userInput.split("\\s+");
        if (tempCommand.length == 1) {
            String[] command = new String[2];
            command[0] = tempCommand[0];
            command[1] = "";
            return command;
        }
        return tempCommand;
    }

    public void terminate() {
        clearScreen();
        resetConsoleColor();
        System.out.println("                        |\n" +
                "                    \\       /\n" +
                "                      .-\"-.\n" +
                "                 --  /     \\  --\n" +
                "`~~^~^~^~^~^~^~^~^~^-=======-~^~^~^~~^~^~^~^~^~^~^~`\n" +
                "`~^_~^~^~-~^_~^~^_~-=========- -~^~^~^-~^~^_~^~^~^~`\n" +
                "`~^~-~~^~^~-^~^_~^~~ -=====- ~^~^~-~^~_~^~^~~^~-~^~`\n" +
                "`~^~^~-~^~~^~-~^~~-~^~^~-~^~~^-~^~^~^-~^~^~^~^~~^~-`");
        resetConsoleColor();

        System.out.print(ConsoleColors.BLACK_UNDERLINED);
        System.out.println("Thank you for using ＼(°o°；）っ ♥ PACKAGE MANAGER ♥\"");
        System.out.println("Have a nice day ahead!");
        System.exit(0);
    }

    public static void clearScreen() {
        for (int i = 0; i < PAGE_SIZE; i++) {
            System.out.println();
        }
    }

}
