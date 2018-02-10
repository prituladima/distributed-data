package com.prituladima.distributed_data.big_json.dto;

public class LocationDTO {

    public Long id;
    public Double latitude;
    public Double longitude;
    public Long time;
    public Long device_id;

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", time=" + time +
                ", device_id=" + device_id +
                '}';
    }
}
