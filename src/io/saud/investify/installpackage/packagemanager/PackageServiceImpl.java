package io.saud.investify.installpackage.packagemanager;

import io.saud.investify.installpackage.common.PackageException;
import io.saud.investify.installpackage.file.PackageFileParserImpl;
import io.saud.investify.installpackage.file.PackageFileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author SAUD
 * @created 25/10/2020 - 7:41 PM
 */
public class PackageServiceImpl implements PackageService {


    PackageFileProcessor packageFileProcessor = new PackageFileParserImpl();
    List<String> allAvailablePackages = new ArrayList<>();
    private Map<String, List<String>> parsedPackages = new HashMap<>();
    private Set<String> installPackages = new HashSet<>();


    public PackageServiceImpl(String fileName) {
        this.init(fileName);
    }

    public void init(String fileName) {
        try {
            List<String> packages = packageFileProcessor.readFile(fileName);
            if (packageFileProcessor.validateFile(packages)) {
                parsedPackages = packageFileProcessor.parsePackages(packages);
            } else {
                throw new PackageException("Oops there is an issue with data parsing with file " + fileName);
            }
            storeAllAvailablePackages();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void install(String packageName) {
        if (allAvailablePackages.contains(packageName)) {
            installPackages.add(packageName);
            installPackages.addAll(parsedPackages.get(packageName));
        }

        installPackages.forEach(System.out::println);

    }

    @Override
    public void remove(String packageName) {
//        packageFileProcessor.
    }

    @Override
    public List<String> list(String args) {

        return allAvailablePackages;
    }


    private void storeAllAvailablePackages() {
        //temp Set for distinct package name
        Set<String> tempAvailablePackages = new HashSet<>();
        parsedPackages.forEach((key, val) -> {
            tempAvailablePackages.add(key);
            tempAvailablePackages.addAll(val.stream().distinct().collect(Collectors.toList()));
        });
        this.allAvailablePackages = tempAvailablePackages.stream().collect(Collectors.toList());
    }
}
