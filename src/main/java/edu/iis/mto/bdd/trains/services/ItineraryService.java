package edu.iis.mto.bdd.trains.services;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class ItineraryService {
	private TimetableService timetableService;

	public ItineraryService(TimetableService timetableService){
		this.timetableService = timetableService;
	}

	public List<LocalTime> findNextDepartures(String departure, String destibation, LocalTime startTime){
		return new ArrayList<>();
	}
}
