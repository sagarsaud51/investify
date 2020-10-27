package io.saud.investify.installpackage.file;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author SAUD
 * @created 25/10/2020 - 8:22 PM
 */
public interface PackageFileProcessor {

    List<String> readFile(String fileName) throws IOException;

    Map<String, List<String>> parsePackages(List<String> fileLines);

    boolean validateFile(List<String> packages);



}
