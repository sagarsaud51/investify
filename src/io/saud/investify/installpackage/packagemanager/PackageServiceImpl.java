package io.saud.investify.installpackage.packagemanager;

import io.saud.investify.installpackage.file.PackageFileParserImpl;
import io.saud.investify.installpackage.file.PackageFileProcessor;

/**
 * @author SAUD
 * @created 25/10/2020 - 7:41 PM
 */
public class PackageServiceImpl implements PackageService {


    PackageFileProcessor packageFileProcessor;

    public PackageServiceImpl(PackageFileProcessor packageFileProcessor) {
        this.packageFileProcessor = packageFileProcessor;
    }

    @Override
    public void install() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void list(String args) {

    }
}
