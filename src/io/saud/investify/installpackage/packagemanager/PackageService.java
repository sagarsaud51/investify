package io.saud.investify.installpackage.packagemanager;

import java.util.List;

/**
 * @author SAUD
 * @created 25/10/2020 - 7:35 PM
 */
public interface  PackageService {

    void install(String packageName);

    void remove(String packageName);

    List<String> list(String args);

}
