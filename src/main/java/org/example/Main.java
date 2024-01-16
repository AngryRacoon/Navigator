package org.example;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        NavigatorImpl navigator = new NavigatorImpl();

        LinkedList<String> locationPoints1 = new LinkedList<>();
        locationPoints1.add("Moscow");
        locationPoints1.add("St. Petersburg");
        Route route1 = new Route("1", 710.0, 6, false, locationPoints1);
        navigator.addRoute(route1);

        LinkedList<String> locationPoints2 = new LinkedList<>();
        locationPoints2.add("St. Petersburg");
        locationPoints2.add("Tambov");
        Route route2 = new Route("2", 1118.0, 3, false, locationPoints2);
        navigator.addRoute(route2);

        LinkedList<String> locationPoints3 = new LinkedList<>();
        locationPoints3.add("Tambov");
        locationPoints3.add("St. Petersburg");
        locationPoints3.add("Moscow");
        Route route3 = new Route("3", 1880, 2, false, locationPoints3);
        navigator.addRoute(route3);

        LinkedList<String> locationPoints4 = new LinkedList<>();
        locationPoints4.add("Tambov");
        locationPoints4.add("Moscow");
        Route route4 = new Route("4", 460, 7, true, locationPoints4);
        navigator.addRoute(route4);

        System.out.println("Size of routes: " + navigator.size());

        System.out.println("Contains route1: " + navigator.contains(route1));
        System.out.println("Contains route3: " + navigator.contains(route3));

        navigator.removeRoute("1");
        System.out.println("Size of routes after removing route1: " + navigator.size());
        System.out.println("Contains route1 after removing: " + navigator.contains(route1));

        navigator.addRoute(route1);

        navigator.chooseRoute("2");
        System.out.println("Popularity of route2 after choosing: " + route2.getPopularity());

        Iterable<Route> searchRoutes = navigator.searchRoutes("Moscow", "Tambov");
        for (Route route : searchRoutes) {
            System.out.println("Search route: " + route);
        }

        Iterable<Route> favoriteRoutes = navigator.getFavoriteRoutes("Moscow");
        for (Route route : favoriteRoutes) {
            System.out.println("Favorite route: " + route);
        }

        Iterable<Route> topRoutes = navigator.getTop3Routes();
        for (Route route : topRoutes) {
            System.out.println("Top route: " + route);
        }
    }
}