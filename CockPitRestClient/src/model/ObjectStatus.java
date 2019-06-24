package model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectStatus {
	
	private String name;
	private String enlisted;
	private String extracted;
	private String inCleaning;
	private String cleaned;
	private String verified;
	private String mapped;
	private String inLoadtest;
	private String transferred;
	private String validated;
	private String inProd;
	public ObjectStatus(String name, String enlisted, String extracted, String inCleaning, String cleaned,
			String verified, String mapped, String inLoadtest, String transferred, String validated, String inProd) {
		super();
		this.name = name;
		this.enlisted = enlisted;
		this.extracted = extracted;
		this.inCleaning = inCleaning;
		this.cleaned = cleaned;
		this.verified = verified;
		this.mapped = mapped;
		this.inLoadtest = inLoadtest;
		this.transferred = transferred;
		this.validated = validated;
		this.inProd = inProd;
	}
	public ObjectStatus() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEnlisted() {
		return enlisted;
	}
	public void setEnlisted(String enlisted) {
		this.enlisted = enlisted;
	}
	public String getExtracted() {
		return extracted;
	}
	public void setExtracted(String extracted) {
		this.extracted = extracted;
	}
	public String getInCleaning() {
		return inCleaning;
	}
	public void setInCleaning(String inCleaning) {
		this.inCleaning = inCleaning;
	}
	public String getCleaned() {
		return cleaned;
	}
	public void setCleaned(String cleaned) {
		this.cleaned = cleaned;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getMapped() {
		return mapped;
	}
	public void setMapped(String mapped) {
		this.mapped = mapped;
	}
	public String getInLoadtest() {
		return inLoadtest;
	}
	public void setInLoadtest(String inLoadtest) {
		this.inLoadtest = inLoadtest;
	}
	public String getTransferred() {
		return transferred;
	}
	public void setTransferred(String transferred) {
		this.transferred = transferred;
	}
	public String getValidated() {
		return validated;
	}
	public void setValidated(String validated) {
		this.validated = validated;
	}
	public String getInProd() {
		return inProd;
	}
	public void setInProd(String inProd) {
		this.inProd = inProd;
	}
	
	public String toJsonString(List<ObjectStatus> bulkObjectStatus) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(bulkObjectStatus);
		return jsonInString;
	}

}
