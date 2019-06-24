package model;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Job {
	
	private String name;
	private String description;
	private String startTime;
	private String endTime;
	private String jobType;
	private int datasetSize;
	private String status;
	private String hostname;
	private String environment;
	private int processedCount;
	private int iteration;
	private int validationPassed;
	private String jobTicket;
	
	public Job(String name, String description, String startTime, String endTime, String jobType, int datasetSize,
			String status, String hostname, String environment, int processedCount, int iteration,
			int validationPassed) {
		super();
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.jobType = jobType;
		this.datasetSize = datasetSize;
		this.status = status;
		this.hostname = hostname;
		this.environment = environment;
		this.processedCount = processedCount;
		this.iteration = iteration;
		this.validationPassed = validationPassed;
	}
	public Job() {
		
	}
	
	public Job(String jobTicket, String endTime, int datasetSize, int processedCount, int validationPassed, String status) {
		this.jobTicket = jobTicket;
		this.endTime = endTime;
		this.datasetSize = datasetSize;
		this.processedCount = processedCount;
		this.validationPassed = validationPassed;
		this.status = status;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public int getDatasetSize() {
		return datasetSize;
	}
	public void setDatasetSize(int datasetSize) {
		this.datasetSize = datasetSize;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public int getProcessedCount() {
		return processedCount;
	}
	public void setProcessedCount(int processedCount) {
		this.processedCount = processedCount;
	}
	public int getIteration() {
		return iteration;
	}
	public void setIteration(int iteration) {
		this.iteration = iteration;
	}
	public int getValidationPassed() {
		return validationPassed;
	}
	public void setValidationPassed(int validationPassed) {
		this.validationPassed = validationPassed;
	}
	
	public String toJsonString(List<Job> bulkJobs) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(bulkJobs);
		return jsonInString;
	}
	
	 

}
