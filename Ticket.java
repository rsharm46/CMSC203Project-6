
enum Format {
		
		IMAX,THREE_D,NONE 
};
	
	

public abstract  class Ticket{
	
	
	private String movieName;
	private String movieRating;
	private int day;
	private int time;
	private String movieType;
	
	
	public Format fmt;
	public Ticket() {
		
	}
	
	public Ticket(String name, String rating, int d, int t, String f){
		
		movieName = name;
		movieRating = rating;
		day = d;
		
		if(t>17.99)
			t = 7;
		else
			t = 5;
			
		time = t;
		movieType = f;
		
		if(f.equals("IMAX")) {
			
			fmt = Format.IMAX;
		}
		else if(f.equals("3D")) {
			
			fmt = Format.THREE_D;
		}
		else if(f.equals("NONE")) {
			
			fmt = Format.NONE;
		}


	}
	
	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieRating() {
		return movieRating;
	}

	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	
	public Format GetFormatType() {
		return fmt;
	}

//	public void setTicketType(String ticketType) {
//		this.ticketType = ticketType;
//	}
	
	public abstract double calculateTicketPrice();
	public abstract int getID();
	public abstract String getTicketType();
	
	
	public String toString() {
		
		return movieType + " " + fmt + " Movie: " + movieName + " Rating: " + movieRating + 
				" Day: " + day +  " Time: " + time + " Price: " +  calculateTicketPrice();
	}
	
}