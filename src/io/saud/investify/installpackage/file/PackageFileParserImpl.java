package io.saud.investify.installpackage.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * @author SAUD
 * @created 25/10/2020 - 7:51 PM
 */
public class PackageFileParserImpl implements PackageFileProcessor {

    private static final Logger LOGGER = Logger.getLogger(PackageFileParserImpl.class.getName());

    private static final String PACKAGE_NAME_SPLITTER = ":";
    private static final String DEPENDENCIES_PACKAGE_NAME_SPLITTER = ",";

    private File file;
    public final List<String> packages = new ArrayList<>();
    public Map<String, List<String>> parsedPackages = new HashMap<>();


    @Override
    public void readFile(String fileName) throws IOException {

        this.file = new File(fileName);
        Stream<String> lines = Files.lines(file.toPath());
        lines.forEach(packages::add);

    }

    @Override
    public Map<String, List<String>> parsePackages() {
        Map<String, List<String>> tempPackages = new HashMap<>();
        packages.forEach(p -> {
            String[] packageDetails = splitStrings(p, PACKAGE_NAME_SPLITTER);


            tempPackages.put(packageDetails[0], packageDetails.length > 1 ? Arrays.asList(splitStrings(packageDetails[1], DEPENDENCIES_PACKAGE_NAME_SPLITTER)) : Collections.singletonList(""));

        });
        return tempPackages;
    }


    @Override
    public boolean validateFile() {
        for (int i = 0; i < packages.size(); i++) {
            if (!packages.get(i).contains(":")) {
                LOGGER.severe("Package File " + file.getName() + " is Not Valid at line " + i);
                return false;
            }
        }
        return true;
    }

    @Override
    public void clearPackages() {
        parsedPackages.clear();
    }

    private String[] splitStrings(String mainString, String splitter) {
        return mainString.split(Pattern.quote(splitter));
    }

}
