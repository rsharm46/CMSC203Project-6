
public class Employee extends Ticket
{

	private String ticketType;
	private int ticketID;
	
	private int numberOfMovies = 0;
	
	public Employee(String name, String rating, int d, int t, String f, String type, int id) 
	{	
		super(name,rating,d,t,f);
		ticketType = type;
		ticketID = id;
		
		numberOfMovies++;
	}
	
	@Override
	public double calculateTicketPrice() 
	{
		double ticketPrice = 0;
		
		if(numberOfMovies <= 2)
			ticketPrice += 0.00;
		else
		{
			if(getTime() < 6)
				ticketPrice += 10.50/2.0;
			else if(getTime() > 6)
				ticketPrice += 13.50/2.0;
		}
		
		ticketPrice += (ticketPrice * .096);
		
		return ticketPrice;
	}

	@Override
	public int getID() 
	{
		return ticketID;
	}
	
	@Override
	public String getTicketType()
	{
		return ticketType;
	}

}
