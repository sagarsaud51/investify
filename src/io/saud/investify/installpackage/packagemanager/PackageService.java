package io.saud.investify.installpackage.packagemanager;

import io.saud.investify.installpackage.common.PackageException;

import java.util.List;

/**
 * @author SAUD
 * @created 25/10/2020 - 7:35 PM
 */
public interface  PackageService {

    void install(String packageName) throws PackageException;

    void remove(String packageName) throws PackageException;

    List<String> list(String args) throws PackageException;

}
