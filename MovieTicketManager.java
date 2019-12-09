import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;



public class MovieTicketManager implements MovieTicketManagerInterface
{

	private NumberFormat currencyFormat;
	private ArrayList<Ticket> ticketList;

	
	public MovieTicketManager() {
		
		ticketList = new ArrayList<Ticket>();

	}
	
	@Override
	public int numVisits(int id) 
	{
		int numVisits = 0;
		
		for(Ticket ticket: ticketList)
		{
			if(ticket.getID() == id)
				numVisits++;
		}
		
		return numVisits;
	}

	@Override
	public int numThisMovie(int id, String movie) {

		int numThisMovie = 0;
		
		for(Ticket ticket: ticketList)
		{
			if(ticket.getID() == id && ticket.getMovieName() == movie)
				numThisMovie++;
		}
		return numThisMovie;
	}

	@Override
	public int numMoviesToday(int id, int date) 
	{
		int numMoviesToday = 0;
		
		for(Ticket ticket: ticketList)
		{
			if(ticket.getID() == id && ticket.getDay() == date)
				numMoviesToday++;
		}
		return numMoviesToday;
	}
	
	public boolean checkFirstMovie(int id)
	{
		boolean firstMovie = false;
		int count = 0;
		for(Ticket ticket: ticketList)
		{
			if(ticket.getID() == id && !ticket.getMovieName().isEmpty())
				count++;
		}
		if(count==1)
			firstMovie = true;
		return firstMovie;
		
	}

	@Override
	public double addTicket(String movieN, String rating, int d, int t, String f, String type, int id) 
	{
		if(type.equals("Adult"))
		{
			Ticket adult = new Adult(movieN, rating, d, t, f, type, id);
			ticketList.add(adult);
			return adult.calculateTicketPrice();
		}
		else if(type.equals("Child"))
		{
			Ticket child = new Child(movieN, rating, d, t, f, type, id);
			ticketList.add(child);
			return child.calculateTicketPrice();
		}
		else if(type.equals("Employee"))
		{
			Ticket employee = new Employee(movieN, rating, d, t, f, type, id);
			ticketList.add(employee);
			return employee.calculateTicketPrice();
		}
		else if(type.equals("MoviePass"))
		{
			int numMoviesToday = 0;
			int numTimesThisMovie = 0;
			MoviePass moviePass = new MoviePass(movieN, rating, d, t, f, type, id);
			numMoviesToday = numMoviesToday(id, d);
			numTimesThisMovie = numThisMovie(id, movieN);
			//System.out.println("MOVIESPERDAY "+ d+ " "+ numMoviesToday +" MOVIENUMTERTIMES "+ movieN+ " "+ numTimesThisMovie);// + moviePass.flag
			ticketList.add(moviePass);
			numMoviesToday += 1;
			numTimesThisMovie += 1;	

			if(checkFirstMovie(id))
				moviePass.firstMovie=true;
			
			if(numMoviesToday>1||numTimesThisMovie>1||f!="NONE")
				moviePass.flag = true;
			
			//System.out.println("MOVIESPERDAY "+ d+ " "+ numMoviesToday +" MOVIENUMTERTIMES "+ movieN+ " "+ numTimesThisMovie);// + moviePass.flag
			//System.out.println("FLAG " + moviePass.flag + " TIMESSeen " + moviePass.timeSeen);
			return moviePass.calculateTicketPrice();
		}
		else
			return -1;
		
	}
	
	
	@Override
	public double totalSalesMonth() 
	{

		double totalSalesMonth = 0;
		int numMoviesToday = 0;
		int numTimesThisMovie = 0;
		for(Ticket ticket: ticketList)
		{
			if(ticket.getTicketType()=="MoviePass")
			{
				numMoviesToday = numMoviesToday(ticket.getID(), ticket.getDay());
				numTimesThisMovie = numThisMovie(ticket.getID(), ticket.getMovieName());
				//System.out.println("MOVIESPERDAY "+  " "+ numMoviesToday +" MOVIENUMTERTIMES "+ " "+ numTimesThisMovie);// + moviePass.flag
				MoviePass p = (MoviePass)ticket;

				if(checkFirstMovie(ticket.getID())) {
					   p.firstMovie = true;
				}
				
				if(Format.THREE_D.equals(ticket.GetFormatType()) 
						|| Format.IMAX.equals(ticket.GetFormatType())) {
					p.flag = true;
					p.firstMovie = false;
				}
			
				if(numMoviesToday>1) {
					p.flag = true;
					p.firstMovie = false;
				}
					
				if(numTimesThisMovie>1) {
					 p.flag = true;
					 p.firstMovie = false;
				}
	
				//System.out.println("TICKET TYPE  " + p.flag);
				System.out.println(p.getMovieName() + " MoviePass Sales " + p.calculateTicketPrice());
				totalSalesMonth += p.calculateTicketPrice();
				//System.out.println("MoviePass Sales " + totalSalesMonth);
			}
			else {
				//System.out.println(ticket.getMovieName() + " Other Sales " + ticket.calculateTicketPrice());
				totalSalesMonth += ticket.calculateTicketPrice();
				
				//System.out.println("Other Sales " + totalSalesMonth);
			}
		}
		System.out.println("Total Sales " + totalSalesMonth);
		return totalSalesMonth;
	}

	@Override
	public String monthlySalesReport() 
	{
		int numMoviesToday = 0;
		int numTimesThisMovie = 0;
		
		double totalAdultPrice = 0;
		int totalNumberAdults = 0;
		
		double totalChild = 0;
		int totalNumberChildren = 0;
		
		double totalEmployee = 0.00;
		int totalNumberEmployees = 0;
		
		double totalMoviePass = 0;
		int totalNumberMoviePasses = 0;
		
		for(Ticket ticket: ticketList)
		{
			if(ticket.getTicketType() == ("Adult"))
			{
				totalAdultPrice += ticket.calculateTicketPrice();
				totalNumberAdults++;
			}
			else if(ticket.getTicketType() == ("Child"))
			{
				totalChild += ticket.calculateTicketPrice();
				totalNumberChildren++;
			}
			else if(ticket.getTicketType() == ("Employee"))
			{
				totalEmployee += ticket.calculateTicketPrice();
				totalNumberEmployees++;
			}
			else if(ticket.getTicketType() == ("MoviePass"))
			{
				numMoviesToday = numMoviesToday(ticket.getID(), ticket.getDay());
				numTimesThisMovie = numThisMovie(ticket.getID(), ticket.getMovieName());
				MoviePass p = (MoviePass)ticket;

				if(checkFirstMovie(ticket.getID())) {
					   p.firstMovie = true;
				}
				
				if(Format.THREE_D.equals(ticket.GetFormatType()) 
						|| Format.IMAX.equals(ticket.GetFormatType())) {
					p.flag = true;
					p.firstMovie = false;
				}
			
				if(numMoviesToday>1) {
					p.flag = true;
					p.firstMovie = false;
				}
					
				if(numTimesThisMovie>1) {
					 p.flag = true;
					 p.firstMovie = false;
				}
				
				totalMoviePass += ticket.calculateTicketPrice();
				totalNumberMoviePasses++;
			}
		}
		String emp = "##.##";
		DecimalFormat decimalFormat = new DecimalFormat(emp);
		String empl = decimalFormat.format(totalEmployee);
		String totalMP = decimalFormat.format(totalMoviePass);
		
		System.out.println(totalMP);
		System.out.println(empl);
		
		return "Monthly Sales Report + \n "
				+ "\n Sales + \n"
				+ "ADULT " + totalAdultPrice + " " + totalNumberAdults + "\n"
				+ "CHILD " + totalChild + " " + totalNumberChildren + "\n"
				+ "EMPLOYEE " + totalEmployee + "0 " + totalNumberEmployees + "\n"
				+ "MOVIEPASS " + totalMP + " " + totalNumberMoviePasses;
	}

	@Override
	public ArrayList<String> get3DTickets() 
	{
		Ticket temp;
		for(int i = 1; i < ticketList.size(); i++)
			for(int j = i; j > 0; j--)
				if(ticketList.get(j).getDay() < ticketList.get(j - 1).getDay())
				{
					temp = ticketList.get(j);
					ticketList.set(j, ticketList.get(j - 1));
					ticketList.set(j - 1, temp);
				}
		
		
		ArrayList<String> threeDTickets = new ArrayList<String>();
		for(Ticket ticket: ticketList)
		{
			if(Format.THREE_D.equals(ticket.GetFormatType()))
			{
				threeDTickets.add(ticket.getMovieName());
			}
		}
		
		return threeDTickets;
	}

	@Override
	public ArrayList<String> getAllTickets() 
	{
		Ticket temp;
		for(int i = 1; i < ticketList.size(); i++)
			for(int j = i; j > 0; j--)
				if(ticketList.get(j).getDay() < ticketList.get(j - 1).getDay())
				{
					temp = ticketList.get(j);
					ticketList.set(j, ticketList.get(j - 1));
					ticketList.set(j - 1, temp);
				}
		
		ArrayList<String> allTickets = new ArrayList<String>();
		for(Ticket ticket: ticketList)
		{
			allTickets.add("Day: " + ticket.getDay());
		}
		
		return allTickets;
	}

	@Override
	public ArrayList<String> getMoviePassTickets() 
	{
		Ticket temp;
		for(int i = 1; i < ticketList.size(); i++)
			for(int j = i; j > 0; j--)
				if(ticketList.get(j).getID() < ticketList.get(j - 1).getID())
				{
					temp = ticketList.get(j);
					ticketList.set(j, ticketList.get(j - 1));
					ticketList.set(j - 1, temp);
				}
		
		ArrayList<String> allMoviePassTickets = new ArrayList<String>();
		for(Ticket ticket: ticketList)
		{
			if(Objects.equals(ticket.getTicketType(), "MoviePass"))
			{
				System.out.println(ticket.getTicketType());
				allMoviePassTickets.add(ticket.getID() + "");
			}
		}
		
		return allMoviePassTickets;
	}

	@Override
	public void readFile(File file) throws FileNotFoundException 
	{
		File ticketFile = file;
		Scanner inFile;
		
		String[] tickets;
		
		try
		{
			inFile = new Scanner(ticketFile);
			
			while(inFile.hasNextLine())
			{
				tickets = inFile.nextLine().split(":");
				
				for(int i = 0; i < tickets.length; i++)
				{
					System.out.print(tickets[i] + ":");
				}
				System.out.println();
				
				addTicket(tickets[0], tickets[1],Integer.parseInt(tickets[2]), Integer.parseInt(tickets[3]), tickets[4], tickets[5], Integer.parseInt(tickets[6]));
				//System.out.println(inFile.nextLine());
				
			}
			
			inFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
	}
	

	
}
