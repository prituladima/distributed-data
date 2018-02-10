package com.prituladima.distributed_data.big_json.dto;

public class Range<T> {

    public Range(T begin, T end) {
        this.begin = begin;
        this.end = end;
    }

    public T begin;
    public T end;

    @Override
    public String toString() {
        return "Range{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }
}
