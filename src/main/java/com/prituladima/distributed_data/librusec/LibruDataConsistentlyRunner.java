package com.prituladima.distributed_data.librusec;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

//All words in archive: 193181077
//Total time: - 168
public class LibruDataConsistentlyRunner {

    public static void main(String[] args) throws IOException {
        long start = new Date().getTime();
        start();
        long finish = new Date().getTime();
        System.out.println("Total time: - " + (finish - start) / 1000);

    }

    public static void start() throws IOException {
        ZipFile zipFile = new ZipFile("/home/prituladima/Downloads/_Lib.rus.ec - Официальная/lib.rus.ec/fb2-153556-158325.zip");

        Enumeration<? extends ZipEntry> entries = zipFile.entries();

        long counter = 0;
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            InputStream stream = zipFile.getInputStream(entry);
            System.out.println(entry.getName());

            counter += new StreamReader().readStream(stream);
        }

        System.out.println("All words in archive: " + counter);

    }

}
