package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.utils.ItineraryServiceBuilder;

public class StandardItineraryService {
    public static ItineraryServiceBuilder builder() {
        return new ItineraryServiceBuilder();
    }
}
