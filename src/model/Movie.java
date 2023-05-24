package model;

public class Movie {
    private int id;      
	private String title;
    private int releasedYear;
	private String genre;
	private String duration;
	private double rating;
	
	
	
	public Movie(String title, int releasedYear, String genre, String duration, double rating) {
		super();
		this.title = title;
		this.releasedYear = releasedYear;
		this.genre = genre;
		this.duration = duration;
		this.rating = rating;
	}
	public Movie(int id, String title, int releasedYear, String genre, String duration, double rating) {
		super();
		this.id = id;
		this.title = title;
		this.releasedYear = releasedYear;
		this.genre = genre;
		this.duration = duration;
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getReleasedYear() {
		return releasedYear;
	}
	public void setReleasedYear(int releasedYear) {
		this.releasedYear = releasedYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Movie [Id=" + id + ", Title=" + title + ", Released Year=" + releasedYear + ", Genre=" + genre
				+ ", Duration=" + duration + ", Rating=" + rating + "]";
	}
	     
	 

	
	}


