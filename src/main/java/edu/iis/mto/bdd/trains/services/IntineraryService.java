package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.List;

public interface IntineraryService {
    List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime);
}
