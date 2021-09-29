package com.propertiesHandler;

import java.util.Properties;


public class AccessProperties {
	
	static Properties property = ReadProperties.readPropertiesFile();
	
	public String getCloseIFrame() {
		return property.getProperty("close_iframe");	
	}
	
	public String getContinueAs() {
		return property.getProperty("continue_as");
	}
	
	public String getHostelBtn() {
		return property.getProperty("hotelsBtn");
	}
	public String getHolidayHomesBtn() {
		return property.getProperty("holidayHomesBtn");
	}
	public String getThingstodoBtn() {
		return property.getProperty("thingstodoBtn");
	}
	public String getRestrauntsBtn() {
		return property.getProperty("restrauntsBtn");
	}
	public String getTarvelforumsBtn() {
		return property.getProperty("tarvelforumsBtn");
	}
	public String getHmMoreBtn() {
		return property.getProperty("hmMoreBtn");
	}
	public String getAddaPlace() {
		return property.getProperty("addaPlace");
	}
	public String getAirlines() {
		return property.getProperty("airlines");
	}
	public String getCarhire() {
		return property.getProperty("carhire");
	}
	public String getCruises() {
		return property.getProperty("cruises");
	}
	public String getFlights() {
		return property.getProperty("flights");
	}
	public String getHelpCentre() {
		return property.getProperty("helpCentre");
	}
	public String getHireTrpDsgnr() {
		return property.getProperty("hireTrpDsgnr");
	}
	public String getPackageHldys() {
		return property.getProperty("packageHldys");
	}
	public String getTravelArticles() {
		return property.getProperty("travelArticles");
	}
	public String getTravellersChoice() {
		return property.getProperty("travellersChoice");
	}
	public String getHomeSearch() {
		return property.getProperty("homeSearch");
	}
	public String getHldySearch() {
		return property.getProperty("hldySearch");
	}
	public String getFirstResult() {
		return property.getProperty("firstResult");
	}
	public String getFrstSearch() {
		return property.getProperty("frstSearch");
	}
	public String getCheckInDiv() {
		return property.getProperty("checkInDiv");
	}
	public String getCheckInDate() {
		return property.getProperty("checkInDate");
	}
	public String getCheckOutDiv() {
		return property.getProperty("checkOutDiv");
	}
	public String getCheckOutDate() {
		return property.getProperty("checkOutDate");
	}
	public String getCalMonth() {
		return property.getProperty("calMonth");
	}
	public String getCalBack() {
		return property.getProperty("calBack");
	}
	public String getCalForward() {
		return property.getProperty("calForward");
	}
	public String getDate() {
		return property.getProperty("date");
	}
	public String getGuestBox() {
		return property.getProperty("guestBox");
	}
	public String getBedrooms() {
		return property.getProperty("bedrooms");
	}
	public String getBedroomsMinus() {
		return property.getProperty("bedroomsMinus");
	}
	public String getBedroomPlus() {
		return property.getProperty("bedroomPlus");
	}
	public String getGuests() {
		return property.getProperty("guests");
	}
	public String getGuestsMinus() {
		return property.getProperty("guestsMinus");
	}
	public String getGuestsPlus() {
		return property.getProperty("guestsPlus");
	}
	public String getBathrooms() {
		return property.getProperty("bathrooms");
	}
	public String getBathroomsMinus() {
		return property.getProperty("bathroomsMinus");
	}
	public String getBathroomsPlus() {
		return property.getProperty("bathroomsPlus");
	}
	public String getApplyBtn() {
		return property.getProperty("applyBtn");
	}
	public String getAmmenitiesShowMore() {
		return property.getProperty("ammenitiesShowMore");
	}
	public String getElevator() {
		return property.getProperty("elevator");
	}
	public String getSortBy() {
		return property.getProperty("sortBy");
	}
	public String getSortRatings() {
		return property.getProperty("sortRatings");
	}
	public String getTotalResults() {
		return property.getProperty("totalResults");
	}
	public String getHomePre() {
		return property.getProperty("homePre");
	}
	public String getHomeTitle() {
		return property.getProperty("homeTitle");
	}
	public String getHomeReview() {
		return property.getProperty("homeReview");
	}
	public String getHomePerNight() {
		return property.getProperty("homePerNight");
	}
	public String getHomeTotal() {
		return property.getProperty("homeTotal");
	}

}
