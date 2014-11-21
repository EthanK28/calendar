package com.mycompany.calendar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mycompany.calendar.dao.CalendarUserDao;
import com.mycompany.calendar.dao.EventAttendeeDao;
import com.mycompany.calendar.dao.EventDao;
import com.mycompany.calendar.domain.Event;
import com.mycompany.calendar.domain.EventAttendee;
import com.mycompany.calendar.domain.EventLevel;
import com.mycompany.calendar.domain.CalendarUser;
import com.mycompany.calendar.service.CalendarService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	//Author: 2008160006 강은서
	
	//@Qualifier("userService")
	@Autowired
	CalendarUserDao calendarUserDao;
	
	@Autowired
	EventDao eventDao;
	
	@Autowired
	EventAttendeeDao eventAttendeeDao;
	
	private CalendarUser[] calendarUsers = null;
	private Event[] events = null;
	private EventAttendee[] eventAttendees = null;
	
	private static final int numInitialNumUsers = 12;
	private static final int numInitialNumEvents = 4;
	private static final int numInitialNumEventAttendees = 12;
	
	private Random random = new Random(System.currentTimeMillis());
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		if(calendarUsers == null && events == null && eventAttendees == null)
			setUp();
		List<CalendarUser> listCalendarUsers = calendarUserDao.findAllusers();
		List<Event> listEvents = eventDao.findAllEvents();
		List<Date> datelist = new ArrayList<Date>();
		
		model.addAttribute("calendarUsers", listCalendarUsers);		
		model.addAttribute("events", listEvents);
		model.addAttribute("eventAttendees", eventAttendees);
		
		return "home";				
	}
	
	public void setUp() {
		calendarUsers = new CalendarUser[numInitialNumUsers];
		events = new Event[numInitialNumEvents];
		eventAttendees = new EventAttendee[numInitialNumEventAttendees];
		
		this.calendarUserDao.deleteAll();
		this.eventDao.deleteAll();
		this.eventAttendeeDao.deleteAll();
		
		for (int i = 0; i < numInitialNumUsers; i++) {
			calendarUsers[i] = new CalendarUser();
			calendarUsers[i].setEmail("user" + i + "@example.com");
			calendarUsers[i].setPassword("user" + i);
			calendarUsers[i].setName("User" + i);
			calendarUsers[i].setId(calendarUserDao.createUser(calendarUsers[i]));
		}
		
		for (int i = 0; i < numInitialNumEvents; i++) {
			events[i] = new Event();
			events[i].setSummary("Event Summary - " + i);
			events[i].setDescription("Event Description - " + i);
			events[i].setOwner(calendarUsers[random.nextInt(numInitialNumUsers)]);
			events[i].setEventLevel(EventLevel.NORMAL);
			switch (i) {				          /* Updated by Assignment 3 */
				case 0:
					events[i].setNumLikes(0);  							
					break;
				case 1:
					events[i].setNumLikes(9);
					break;
				case 2:
					events[i].setNumLikes(10);
					break;
				case 3:
					events[i].setNumLikes(10);
					break;
			}
			events[i].setId(eventDao.createEvent(events[i]));
		}
		
		for (int i = 0; i < numInitialNumEventAttendees; i++) {
			eventAttendees[i] = new EventAttendee();
			eventAttendees[i].setEvent(events[i % numInitialNumEvents]);
			eventAttendees[i].setAttendee(calendarUsers[i]);
			eventAttendees[i].setId(eventAttendeeDao.createEventAttendee(eventAttendees[i]));
		}
	}
	
	
}

