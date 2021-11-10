package com.cloud7mu7.searchplaceapp;

import java.util.List;

public class SearchLocalApiResponse {
    PlaceMeta meta;
    List<Place> documents;
}

class PlaceMeta{
    int total_count;
    int pageable_count;
    boolean is_end;
}
class Place{
    String place_name;
    String phone;
    String address_name;
    String road_address_name;
    String x;
    String y;
    String place_url;
    String distance;
}