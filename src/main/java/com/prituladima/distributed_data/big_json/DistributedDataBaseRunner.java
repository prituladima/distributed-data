package com.prituladima.distributed_data.big_json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.prituladima.distributed_data.big_json.dto.LocationDTO;
import com.prituladima.distributed_data.big_json.dto.Range;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DistributedDataBaseRunner {

    public static void main(String[] args) throws Exception {

        long timeStart = new Date().getTime();
        Type REVIEW_TYPE = new TypeToken<List<LocationDTO>>() {
        }.getType();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(new File("external_data/bigdata_300.json")));
        List<LocationDTO> data = gson.fromJson(reader, REVIEW_TYPE);

        System.out.println("Size - " + data.size());

        for (int j = 0; j < 10; j++) {
            System.out.println(data.get(j));
        }

        long timeFinish = new Date().getTime();

        System.out.println("Time - " + (timeFinish - timeStart) / 1000);

        Map<Long, LocationDTO> sortedMapById = new TreeMap<>();
        for (LocationDTO current : data) {
            sortedMapById.put(current.id, current);
        }

        System.out.println("sortedMapById.size() = " + sortedMapById.size());

        SortedMap<Long, LocationDTO> sortedMapByTime = new TreeMap<>();
        for (LocationDTO current : data) {
            sortedMapByTime.put(current.time, current);
        }
        System.out.println("------Time-----");

        int size = sortedMapByTime.size();
        int rangeCounter = 0;
        for (Range<Long> range : timeDistribution(getBeginDate(), getStep(), getEndDate())) {

            rangeCounter++;
            SortedMap<Long, LocationDTO> mapPeriod = sortedMapByTime.subMap(range.begin, range.end);
            int sizeForPeriod = mapPeriod.size();
            Double fraction = (sizeForPeriod * 1.0) / size;
            Long percent = Math.round(fraction * 100);
            System.out.println("From: " + new Date(range.begin) + "size = " + sizeForPeriod + " percent = " + percent + "%" + " -> " + getLineFromPercent(fraction));
        }

        rangeCounter = 0;



        System.out.println("------Latitude-----");
        SortedMap<Double, LocationDTO> sortedMapByLatitude = new TreeMap<>();
        for (LocationDTO current : data) {
            sortedMapByLatitude.put(current.latitude, current);
        }
        int sizesortedMapByLatitude = sortedMapByLatitude.size();
        rangeCounter = 0;
        for (Range<Double> range : getLatLonUniversalDistribution(90.0, 15.0, -90.0)) {

            rangeCounter++;
            SortedMap<Double, LocationDTO> mapPeriod = sortedMapByLatitude.subMap(range.begin, range.end);
            int sizeForPeriod = mapPeriod.size();
            Double fraction = (sizeForPeriod * 1.0) / sizesortedMapByLatitude;
            Long percent = Math.round(fraction * 100);
            System.out.println("From: " + range.begin + "size = " + sizeForPeriod + " percent = " + percent + "%" + " -> " + getLineFromPercent(fraction));
        }

        System.out.println("------Longitude-----");
        SortedMap<Double, LocationDTO> sortedMapByLongitude = new TreeMap<>();
        for (LocationDTO current : data) {
            sortedMapByLongitude.put(current.longitude, current);
        }
        int sizesortedMapByLongitude = sortedMapByLatitude.size();
        rangeCounter = 0;
        for (Range<Double> range : getLatLonUniversalDistribution(180.0, 15.0, -180.0)) {

            rangeCounter++;
            SortedMap<Double, LocationDTO> mapPeriod = sortedMapByLongitude.subMap(range.begin, range.end);
            int sizeForPeriod = mapPeriod.size();
            Double fraction = (sizeForPeriod * 1.0) / sizesortedMapByLatitude;
            Long percent = Math.round(fraction * 100);
            System.out.println("From: " + range.begin + "size = " + sizeForPeriod + " percent = " + percent + "%" + " -> " + getLineFromPercent(fraction));
        }

    }


    public static List<Range<Long>> timeDistribution(long begin, long step, long end) {
        assert begin < end;
        assert step < end - begin;
        List<Range<Long>> distribution = new ArrayList<>();
        for (long index = begin; index < end; index += step) {
            distribution.add(new Range<>(index, index + step));
        }
        return distribution;
    }

    public static long getBeginDate() {
        return new Calendar.Builder().setDate(2017, 10, 10).build().getTimeInMillis();
    }

    public static long getEndDate() {
        return new Calendar.Builder().setDate(2018, 1, 10).build().getTimeInMillis();
    }

    public static long getStep() {
        return TimeUnit.DAYS.toMillis(1);
    }

    public static String getLineFromPercent(double fraction) {

        final int size = 300;
        final int outPutSize = (int) (size * fraction);
        final char defaultCharacter = '#';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= outPutSize; i++)
            stringBuilder.append(defaultCharacter);
        return stringBuilder.toString();

    }


    public static List<Range<Double>> getLatLonUniversalDistribution(double begin, double step, double end) {
        assert begin < end;
        assert step < end - begin;
        List<Range<Double>> distribution = new ArrayList<>();
        for (double index = begin; index > end; index -= step) {
            distribution.add(new Range<>(index, index + step));
        }
        return distribution;
    }


}