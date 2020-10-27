package io.saud.investify.installpackage;


import io.saud.investify.installpackage.packagemanager.PackageService;
import io.saud.investify.installpackage.packagemanager.PackageServiceImpl;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author SAUD
 * @created 25/10/2020 - 10:39 PM
 */
public class CLIPackageManager {


    public static void main(String[] args) throws Exception {
        PackageService packageService = new PackageServiceImpl("package.txt");
        packageService.install("E");
        packageService.install("D");
        packageService.install("C");
        packageService.install("B");
        packageService.install("A");

        try{
            packageService.remove("C");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
