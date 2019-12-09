import java.util.ArrayList;

public class MoviePass extends Ticket{

	private String ticketType;
	private int ticketID;
	public boolean flag;
	public boolean firstMovie;
	public int timeSeen = 0;
	
	private double ticketPrice;
	
	public MoviePass()
	{
		
	}
	public MoviePass(String name, String rating, int d, int t, String f, String type, int id) 
	{
		super(name,rating,d,t,f);
		ticketType = type;
		ticketID = id;
		
		firstMovie = false;
		flag = false;

		ticketPrice = 0.00;
	}
	
	
	@Override
	public double calculateTicketPrice() 
	{
		ticketPrice = 0.0;
		
		if(firstMovie)
			ticketPrice = 9.99;
		else 
		{
			if(flag) {
					ticketPrice = 0;
					if(super.fmt == Format.IMAX)
						ticketPrice += 3.00;
					else if(super.fmt == Format.THREE_D)
						ticketPrice += 2.5;
					if(getTime() < 6)
						ticketPrice += 10.50;
					else if(getTime() > 6)
						ticketPrice += 13.50;
			}
			else
				ticketPrice = 0.00;
			
			ticketPrice += (ticketPrice * .096);
		}
		return ticketPrice;
	}
	
	public void calcPrice(boolean bool)
	{
		if(bool == true)
		{
			ticketPrice += 0.00;
		}
		else
		{
			if(super.fmt == Format.IMAX)
				ticketPrice += 3.00;
			else if(super.fmt == Format.THREE_D)
				ticketPrice += 2.5;
			
			if(getTime() < 6)
				ticketPrice += 10.50;
			else if(getTime() > 6)
				ticketPrice += 13.50;
			
			ticketPrice += (ticketPrice * .096);
		}
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
