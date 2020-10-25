package io.saud.investify.installpackage.file;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author SAUD
 * @created 25/10/2020 - 8:22 PM
 */
public interface PackageFileProcessor {

    void readFile(String fileName) throws IOException;

    Map<String, List<String>> parsePackages();

    boolean validateFile() throws Exception;

    void clearPackages();

}
