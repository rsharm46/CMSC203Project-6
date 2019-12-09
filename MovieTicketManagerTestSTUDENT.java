import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MovieTicketManagerTestSTUDENT {
	private MovieTicketManager ticketList;
	

	@Before
	public void setUp() throws Exception {
		ticketList = new MovieTicketManager();
		
		//add adults
		ticketList.addTicket("Suicide Squad", "NR", 7,21,"3D","Adult",0);
		ticketList.addTicket("Frozen", "PG", 3,3,"NONE","Adult",0);
				
		//add children
		ticketList.addTicket("Up", "G", 9,13,"IMAX","Child",0);
		ticketList.addTicket("X-Men", "PG13", 1,12,"NONE","Child",0);
				
		//add employees
		ticketList.addTicket("Joker", "NR", 10, 17,"NONE","Employee",79415);
		ticketList.addTicket("The Irishman", "NR", 11,21,"3D","Employee",23456);
				
		//add MoviePass members
		ticketList.addTicket("Angel Has Fallen", "NR", 6,13,"NONE","MoviePass",22222);
		ticketList.addTicket("Avengers", "PG13", 2,18,"IMAX","MoviePass",55555);
	}

	@After
	public void tearDown() throws Exception {
		//set tickets to null
	}

	/**
	 * Test the number of visits to the theater within the month
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumVisits() {
		//Employee
		assertEquals(1,ticketList.numVisits(79415));
		ticketList.addTicket("Boba Fett", "PG13", 9,7,"NONE","Employee",79415);
		assertEquals(2,ticketList.numVisits(79415));
		ticketList.addTicket("Skywalker", "PG13", 11,3,"NONE","Employee",79415);
		assertEquals(3,ticketList.numVisits(79415));
				
		//MoviePass member
		assertEquals(1,ticketList.numVisits(22222));
		ticketList.addTicket("Equaliser", "NR", 6,6,"NONE","MoviePass",22222);
		assertEquals(2,ticketList.numVisits(22222));
		ticketList.addTicket("Double Action Boogaloo", "NR", 12,1,"NONE","MoviePass",22222);
		assertEquals(3,ticketList.numVisits(22222));
		
	}

	/**
	 * Test the number of times this movie has been viewed
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumThisMovie() {
		//Employee
		assertEquals(1,ticketList.numThisMovie(79415,"Joker"));
		ticketList.addTicket("Cyber Driver", "PG13", 5,14,"NONE","Employee",12345);
		assertEquals(1,ticketList.numThisMovie(12345,"Cyber Driver"));

		//MoviePass member
		assertEquals(1,ticketList.numThisMovie(22222,"Angel Has Fallen"));
		ticketList.addTicket("Watch Dogs", "NR", 1,12,"NONE","MoviePass",22222);
		assertEquals(1,ticketList.numThisMovie(22222,"Watch Dogs"));

	}

	/**
	 * Test the number of movies attended on a day
	 * This only applied to those who have id members - Employees or MoviePass members
	 */
	@Test
	public void testNumMoviesToday() {
		//Employee
		assertEquals(1,ticketList.numMoviesToday(79415,10));
		ticketList.addTicket("Boba Fett", "PG13", 9,7,"NONE","Employee",79415);
		assertEquals(1,ticketList.numMoviesToday(79415,9));
		ticketList.addTicket("Boba Fett", "PG13", 11,3,"NONE","Employee",79415);
		assertEquals(1,ticketList.numMoviesToday(79415,11));
				
		//MoviePass member
		assertEquals(1,ticketList.numMoviesToday(22222,6));
		ticketList.addTicket("Equaliser", "NR", 6,6,"NONE","MoviePass",22222);
		assertEquals(2,ticketList.numMoviesToday(22222,6));
		ticketList.addTicket("Double Action Boogaloo", "NR", 12,1,"NONE","MoviePass",22222);
		assertEquals(1,ticketList.numMoviesToday(22222,12));
	}

	/**
	 * Test adding tickets of the 4 types of tickets
	 */
	@Test
	public void testAddTicket() {
		
		MovieTicketManager tickets = new MovieTicketManager();
		
		assertEquals(14.80,tickets.addTicket("Cyber Driver", "PG13", 5,19,"NONE","Adult",0),.01);
		assertEquals(11.78,tickets.addTicket("Hercules", "G", 5,19,"NONE","Child",0),.01);
		assertEquals(0.00,tickets.addTicket("Joker", "NR", 5,19,"NONE","Employee",79415),.01);
		assertEquals(9.99,tickets.addTicket("Angel Has Fallen", "NR", 5,19,"NONE","MoviePass",22222),.01);
	}

	/**
	 * Test the total of tickets sales for the month
	 */
	@Test
	public void testTotalSalesMonth() {
		fail("Not yet implemented");
	}

	/**
	 * The 3D tickets sold this month in chronological order by day
	 */
	@Test
	public void testGet3DTickets() {

		ArrayList<String> result = ticketList.get3DTickets();
		assertTrue("Day 2",result.get(0).contains("Show Dogs"));
		assertTrue("Day 3",result.get(1).contains("Black Panther"));
		ticketList.addTicket("Life of the Party", "PG13", 6,21,"3D","Employee",56789);
		ticketList.addTicket("Adrift", "PG", 1,21,"3D","MoviePass",665544);
		result = ticketList.get3DTickets();
		assertTrue("Day 1",result.get(0).contains("Adrift"));
		assertTrue("Day 2",result.get(1).contains("Show Dogs"));
		assertTrue("Day 3",result.get(2).contains("Black Panther"));
		assertTrue("Day 6",result.get(3).contains("Life of the Party"));
		
	}

	/**
	 * All tickets sold this month in chronological order by day
	 * You don't need to worry about ordering within the day
	 */
	@Test
	public void testGetAllTickets() {
		
		ArrayList<String> result = ticketList.getAllTickets();
		assertTrue("Day 1",result.get(0).contains("Day: 1"));
		assertTrue("Day 1",result.get(1).contains("Day: 1"));
		assertTrue("Day 2",result.get(2).contains("Day: 2"));
		assertTrue("Day 2",result.get(3).contains("Day: 2"));
		assertTrue("Day 2",result.get(4).contains("Day: 2"));
		assertTrue("Day 2",result.get(5).contains("Day: 2"));
		assertTrue("Day 2",result.get(6).contains("Day: 2"));
		assertTrue("Day 3",result.get(7).contains("Day: 3"));
		assertTrue("Day 4",result.get(8).contains("Day: 4"));
		assertTrue("Day 5",result.get(9).contains("Day: 5"));
		assertTrue("Day 5",result.get(10).contains("Day: 5"));
		assertTrue("Day 5",result.get(11).contains("Day: 5"));
	}

	/**
	 * The MoviePass tickets sold this month in order by MoviePass id
	 */
	@Test
	public void testGetMoviePassTickets() {
		ArrayList<String> result = ticketList.getMoviePassTickets();
		
		for(String st: result)
			System.out.println(st);
		
		assertTrue("22222",result.get(0).contains("22222"));
		assertTrue("33333",result.get(1).contains("33333"));
		assertTrue("55555",result.get(2).contains("55555"));
	}

	/**
	 * The monthly sales report
	 */
	@Test
	public void testMonthlySalesReport(){
		fail("Not yet implemented");
		
	}
	
	/**
	 * Test readin from a file
	 * @throws FileNotFoundException when file is not found
	 */
	@Test
	public void testReadFile() throws FileNotFoundException {
		fail("Not yet implemented");
	}

}
