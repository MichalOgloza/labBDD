package edu.iis.mto.bdd.trains.services;

import edu.iis.mto.bdd.trains.model.Line;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.List;

public class ItineraryService {
	private TimetableService timetableService;
	private Line line;
	private Minutes INTERVAL = Minutes.minutes(15);

	public ItineraryService(Line line, TimetableService timetableService){
		this.line = line;
		this.timetableService = timetableService;
	}

	public void setInterval(int minutes){
		this.INTERVAL = Minutes.minutes(minutes);
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public List<LocalTime> findNextDepartures(String departure, String destination, LocalTime startTime){
		List<Line> lines = timetableService.findLinesThrough(departure, destination);
		if(!lines.contains(line)){
			throw new IllegalArgumentException("No line data in timetable.");
		}
		return getNextArrivalTimes(startTime, timetableService.findArrivalTimes(line, departure));
	}

	private List<LocalTime> getNextArrivalTimes(LocalTime startTime, List<LocalTime> arrivalTimes){
		List<LocalTime> results = new ArrayList<>();
		arrivalTimes.forEach(localTime -> {if(isInInterval(startTime, localTime)) results.add(localTime);});
		return results;
	}

	private boolean isInInterval(LocalTime startTime, LocalTime localTime){
		return Minutes.minutesBetween(startTime, localTime).isLessThan(INTERVAL)
				&& startTime.isBefore(localTime);
	}
}
