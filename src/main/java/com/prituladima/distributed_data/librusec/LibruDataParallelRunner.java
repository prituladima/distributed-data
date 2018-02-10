package com.prituladima.distributed_data.librusec;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

//All words in archive: 193181077
//Total time: - 78
public class LibruDataParallelRunner {

    public static void main(String[] args) throws Exception {


        long start = new Date().getTime();
        start();
        long finish = new Date().getTime();
        System.out.println("Total time: - " + (finish - start) / 1000);

    }


    public static void start() throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2 * Runtime.getRuntime().availableProcessors());
        List<Future<Long>> longFuture = service.invokeAll(getCallebleList("/home/prituladima/Downloads/_Lib.rus.ec - Официальная/lib.rus.ec/fb2-153556-158325.zip"));

        service.shutdown();

        long counter = 0;
        for (Future<Long> future : longFuture) {
            counter += future.get();
        }
        System.out.println("All words in archive: " + counter + "futureList size  - " + longFuture.size());
    }

    public static List<Callable<Long>> getCallebleList(String path) {
        List<Callable<Long>> list = new ArrayList<>();

        List<InputStream> inputStreamList = getInputStreams(path);

        for (InputStream stream : inputStreamList)
            list.add(() -> new StreamReader().readStream(stream));

        return list;
    }

    public static List<InputStream> getInputStreams(String path) {
        List<InputStream> list = new ArrayList<>();
        try {
            ZipFile zipFile = new ZipFile(path);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                list.add(zipFile.getInputStream(entry));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return list;
        }
    }

}
