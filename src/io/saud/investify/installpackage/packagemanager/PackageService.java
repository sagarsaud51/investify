package io.saud.investify.installpackage.packagemanager;

/**
 * @author SAUD
 * @created 25/10/2020 - 7:35 PM
 */
public interface  PackageService {

    void install();

    void remove();

    void list(String args);

}
