package model;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UpdateJob {
	
	private String jobTicket;
	private String endTime;
	private int datasetSize;
	private int processedCount;
	private int validationPassed;
	private String status;
	
	public UpdateJob(String jobTicket, String endTime, int i, int j,
			int k, String status) {
		super();
		this.jobTicket = jobTicket;
		this.endTime = endTime;
		this.datasetSize = i;
		this.processedCount = j;
		this.validationPassed = k;
		this.status = status;
	}
	
	public UpdateJob() {}

	public String getJobTicket() {
		return jobTicket;
	}

	public void setJobTicket(String jobTicket) {
		this.jobTicket = jobTicket;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDatasetSize() {
		return datasetSize;
	}

	public void setDatasetSize(int datasetSize) {
		this.datasetSize = datasetSize;
	}

	public int getProcessedCount() {
		return processedCount;
	}

	public void setProcessedCount(int processedCount) {
		this.processedCount = processedCount;
	}

	public int getValidationPassed() {
		return validationPassed;
	}

	public void setValidationPassed(int validationPassed) {
		this.validationPassed = validationPassed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String toJsonString(List<UpdateJob> bulkJobs) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(bulkJobs);
		return jsonInString;
	}

}
