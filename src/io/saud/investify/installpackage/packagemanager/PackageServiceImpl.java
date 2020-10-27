package io.saud.investify.installpackage.packagemanager;

import io.saud.investify.installpackage.common.PackageException;
import io.saud.investify.installpackage.file.PackageFileParserImpl;
import io.saud.investify.installpackage.file.PackageFileProcessor;
import org.w3c.dom.ls.LSOutput;

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
    public void install(String packageName) throws PackageException {
        if (checkIfPackageIsAvailable(packageName)) {

            if (installPackages.containsAll(parsedPackages.get(packageName))) {
                installPackages.add(packageName);
            } else {
                throw new PackageException(packageName + " cannot be add because you need to install " + parsedPackages.get(packageName).toString() + " first");
            }

        } else {
            throw new PackageException("Package name " + packageName + " is not found in our Package Repository");
        }

    }

    @Override
    public void remove(String packageName) throws PackageException {
        installPackages.forEach(System.out::println);
        if (installPackages.contains(packageName)) {


            Map<String,List<String>>
                    tempPackages = parsedPackages
                    .entrySet()
                    .stream()
                    .filter(i-> i.getValue().contains(packageName) && installPackages.contains(i.getKey()))
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));


            if (tempPackages.size() == 0) {
                installPackages.remove(packageName);
            } else {
                throw new PackageException(packageName + " cannot be removed because installed package " + tempPackages.keySet().toString() + " depends on it");
            }


        } else {
            throw new PackageException("Package Not Found");
        }
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
        this.allAvailablePackages = new ArrayList<>(tempAvailablePackages);
    }

    private boolean checkIfPackageIsAvailable(String packageName){
        return allAvailablePackages.contains(packageName);
    }


    private String removeExceptionMessageBuilder(Map<String,List<String>> dependenciesPackages){
        return null;
    }
}
