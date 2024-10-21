package org.epower.model;

import java.util.ArrayList;
import java.util.List;

public class Location {

    private String name;
    private List<ChargingStation> stations;

    public Location(String name) {
        this.name = name;
        this.stations = new ArrayList<>();
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public List<ChargingStation> getStations() {
        return stations;
    }

    public void addChargingStation(ChargingStation station) {
        stations.add(station);
    }

    public void removeChargingStation(String stationId) {
        stations.removeIf(station -> station.getStationId().equals(stationId));
    }

    public ChargingStation getStationById(String stationId) {
        return stations.stream().filter(station -> station.getStationId().equals(stationId)).findFirst().orElse(null);
    }
}