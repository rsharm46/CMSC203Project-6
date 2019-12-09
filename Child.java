

public class Child extends Ticket{

	private String ticketType;
	private int ticketID;
	
	public Child(String name, String rating, int d, int t, String f, String type, int id) {
		
		super(name,rating,d,t,f);
		ticketType = type;
		ticketID = id;
	}
	
	@Override
	public double calculateTicketPrice() 
	{
		double totalPrice = 0;
		
		if(getTime() < 6)
			totalPrice += 5.75;
		else if(getTime() > 6)
			totalPrice += 10.75;
		
		if(super.fmt == Format.IMAX)
			totalPrice += 2.00;
		else if(super.fmt == Format.THREE_D)
			totalPrice += 1.50;
		
		
		totalPrice += (totalPrice * .096);
		
		return totalPrice;
	}

	@Override
	public int getID() 
	{
		return -1;
	}
	
	@Override
	public String getTicketType()
	{
		return ticketType;
	}

}
