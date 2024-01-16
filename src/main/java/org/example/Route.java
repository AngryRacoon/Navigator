package org.example;

import java.util.LinkedList;
import java.util.Objects;

public class Route {
    private String ID;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    LinkedList<String> locationPoints;

    public Route(String ID, double distance, int popularity, boolean isFavorite, LinkedList<String> locationPoints) {
        this.ID = ID;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.distance, distance) == 0 &&
                Objects.equals(locationPoints.getFirst(), route.locationPoints.getFirst()) &&
                Objects.equals(locationPoints.getLast(), route.locationPoints.getLast());
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationPoints.getFirst(), distance, locationPoints.getLast());
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public LinkedList<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(LinkedList<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public String toString() {
        return locationPoints.getFirst() + " - " + locationPoints.getLast() + " (" + distance + " km)";
    }
}
