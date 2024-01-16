package org.example;

import java.util.Comparator;
import java.util.Iterator;

public class NavigatorImpl implements Navigator {

    LinkedList<Route> routes;

    public NavigatorImpl() {
        routes = new LinkedList<>();
    }
    @Override
    public void addRoute(Route route) {
        if (this.contains(route)){
            return;
        }
        routes.add(route);
    }

    @Override
    public void removeRoute(String routeId) {
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            if (route.getID().equals(routeId)) {
                routes.remove(i);
                break;
            }
        }
    }

    @Override
    public boolean contains(Route route) {
        for (Route r: routes) {
            if (r.equals(route)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return routes.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (Route route : routes) {
            if (route.getID().equals(routeId)) {
                return route;
            }
        }
        return null;
    }

    @Override
    public void chooseRoute(String routeId) {
        for (Route route : routes) {
            if (route.getID().equals(routeId)) {
                route.setPopularity(route.getPopularity() + 1);
                break;
            }
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        LinkedList<Route> searchRoutes = new LinkedList<>();

        for (Route route : routes) {
            if (route.getLocationPoints().contains(startPoint) && route.getLocationPoints().contains(endPoint)) {
                searchRoutes.add(route);
            }
        }

        searchRoutes.sort(new Comparator<Route>() {
            @Override
            public int compare(Route r1, Route r2) {
                int favoriteComparison = Boolean.compare(r2.isFavorite(), r1.isFavorite());
                if (favoriteComparison != 0) {
                    return favoriteComparison;
                } else {
                    int distanceComparison = Integer.compare(r1.getLocationPoints().indexOf(startPoint) - r1.getLocationPoints().indexOf(endPoint),
                            r2.getLocationPoints().indexOf(startPoint) - r2.getLocationPoints().indexOf(endPoint));
                    if (distanceComparison != 0) {
                        return distanceComparison;
                    } else {
                        return Integer.compare(r2.getPopularity(), r1.getPopularity());
                    }
                }
            }
        });

        return searchRoutes;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        LinkedList<Route> favoriteRoutes = new LinkedList<>();

        for (Route route : routes) {
            if (route.isFavorite() && route.getLocationPoints().contains(destinationPoint) && !route.getLocationPoints().getFirst().equals(destinationPoint)) {
                favoriteRoutes.add(route);
            }
        }

        favoriteRoutes.sort(new Comparator<Route>() {
            @Override
            public int compare(Route r1, Route r2) {
                int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
                if (distanceComparison != 0) {
                    return distanceComparison;
                } else {
                    return Integer.compare(r2.getPopularity(), r1.getPopularity());
                }
            }
        });
        return favoriteRoutes;
    }



    @Override
    public Iterable<Route> getTop3Routes() {
        LinkedList<Route> topRoutes = new LinkedList<>();

        for (Route route : routes) {
            topRoutes.add(route);
        }

        topRoutes.sort(new Comparator<Route>() {
            @Override
            public int compare(Route r1, Route r2) {
                int popularityComparison = Integer.compare(r2.getPopularity(), r1.getPopularity());
                if (popularityComparison != 0) {
                    return popularityComparison;
                } else {
                    int distanceComparison = Double.compare(r1.getDistance(), r2.getDistance());
                    if (distanceComparison != 0) {
                        return distanceComparison;
                    } else {
                        return Integer.compare(r1.getLocationPoints().size(), r2.getLocationPoints().size());
                    }
                }
            }
        });

        LinkedList<Route> result = new LinkedList<>();
        Iterator<Route> iterator = topRoutes.iterator();
        for (int i = 0; i < 3 && iterator.hasNext(); i++) {
            result.add(iterator.next());
        }

        return result;
    }
}
