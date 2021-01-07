package com.adc.da;

import java.io.File;

import io.xjar.jar.XJar;

public class XJarer {

    public static void main(String[] args) throws Exception{
        String password = "io.xjar";
        File plaintext = new File("D:\\repository\\com\\adc\\adc-da-login\\2.3.81\\adc-da-login-2.3.81.jar");
        File encrypted = new File("D:\\repository\\com\\adc\\adc-da-login\\2.3.81\\adc-da-login-2.3.82.jar");
        XJar.encrypt(plaintext, encrypted, password);
//        XBoot.encrypt(plaintext, encrypted, password, new XJarArchiveEntryFilter() {
//            @Override
//            public boolean filter(JarArchiveEntry entry) {
//                return entry.getName().startsWith("/BOOT-INF/classes/")
//                        || entry.getName().startsWith("/BOOT-INF/lib/jar-need-encrypted");
//            }
//        });
    }
}