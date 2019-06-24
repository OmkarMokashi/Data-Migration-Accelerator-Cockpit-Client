package restclient;
/**
 * 
 */

/**
 * @author omkar.m
 *
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import model.FileStatus;
import model.Job;
import model.ObjectStatus;
import model.UpdateJob;

/**
 * <h1>Cockpit REST Client</h1>
 * The CockPitRestClient class provides methods to add and retrieve
 * data to the mean stack server. 
 * 
 * @author omkar.m
 *
 */
public class CockPitRestClient {

	/**
	 * This is the main method which controls the call of all the adding and fetching data functions
	 * provided by the CockPitRestClient class.
	 * @param args Unused
	 * @return Nothing
	 * @throws Exception Handles all the exception
	 */
	public static void main(String[] args) throws Exception {
		
		Properties prop = new Properties();
		InputStream input = new FileInputStream("restclient.properties");
		prop.load(input);
		
		System.out.println(prop.getProperty("protocol"));
		System.out.println(prop.getProperty("host"));
		System.out.println(prop.getProperty("port"));
		
		String fetchJobsUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/jobs/fetchJobs";
		String addJobsUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/jobs/createJob";
		String fetchProgressUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/progress/fetchProgress";
		String fetchAssessmentUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/assessment/fetchAssessments";
		String addAssessmentUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/assessment/addAssessment";
		String fetchFileStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/filestatus/fetchFileStatus";
		String addFileStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/filestatus/addFileStatus";
		String fetchObjectStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/objectstatus/fetchObjectStatus";
		String addObjectStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/objectstatus/addObjectStatus";
		String bulkCreateJobsUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/jobs/api/addBulkJobs";
		String bulkAddFileStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/filestatus/api/addBulkFileStatus";
		String blukAddObjectStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/objectstatus/api/addBulkObjectStatus";
		String bulkUpdateJobsUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/jobs/api/bulkUpdateJobs";
		String bulkUpdateFileStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/filestatus/api/bulkUpdateFileStatus";
		String bulkUpdateObjectStatusUrl = prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/cockpit/services/objectstatus/api/bulkUpdateObjectStatus";
		
		CockPitRestClient http = new CockPitRestClient();
		
		boolean condition = true;
		
		while(condition) {
			System.out.println("1. Fetch Jobs");
			System.out.println("2. Add Jobs");
			System.out.println("3. Fetch Progress");
			System.out.println("4. Fetch Assessment");
			System.out.println("5. Add Assessment");
			System.out.println("6. Fetch File Status");
			System.out.println("7. Add File Status");
			System.out.println("8. Fetch Object Status");
			System.out.println("9. Add Object Status");
			System.out.println("10, Create bulk jobs");
			System.out.println("11. Add Bulk File Status");
			System.out.println("12. Add Bulk Object Status");
			System.out.println("13. Update Bulk Jobs");
			System.out.println("14. Update Bulk File Status");
			System.out.println("15. Update Bulk Object Status");
			System.out.println("16. Exit");
			System.out.print("Enter your option : ");
			
			Scanner scanner = new Scanner(System.in);
			int option = scanner.nextInt();
			
			switch(option) {
			case 1:
				http.fetchJobs(fetchJobsUrl, "2019", "4", "15", "2019", "5", "15");
				break;
			case 2:
				HashMap<String,String> map = new HashMap<>();
				
				map.put("name", "Updating Drawing");
				map.put("description", "Updation of Drawing");
				map.put("startTime", "05/12/2019 13:16:12");
				map.put("endTime", "05/13/2019 22:43:17");
				map.put("jobType", "Drawing");
				map.put("datasetSize", "2500");
				map.put("status", "Completed");
				map.put("hostname", "DS-INA745TJS6");
				map.put("environment", "DEV");
				map.put("processedCount", "2400");
				map.put("iteration", "4");
				map.put("validationPassed", "90");
				
				http.addJobs(addJobsUrl,map);
				break;
			case 3:
				http.fetchProgress(fetchProgressUrl, "2019", "4", "15", "2019", "5", "15");
				break;
			case 4:
				http.fetchAssessments(fetchAssessmentUrl);
				break;
			case 5:
				HashMap<String, Integer> assessmentMap = new HashMap<>();
				
				assessmentMap.put("Part", 12000);
				assessmentMap.put("Drawing", 10000);
				assessmentMap.put("EBOM", 14000);
				assessmentMap.put("MBOM", 9000);
				assessmentMap.put("Documents", 8000);
				assessmentMap.put("Part Specification", 5000);
				assessmentMap.put("Change Request", 6000);
				assessmentMap.put("Change Order", 8000);
				ArrayList<HashMap<String, String>> assessmentList = new ArrayList<>();
				
				http.addAssessment(addAssessmentUrl, assessmentMap);
				break;
			case 6:
				http.fetchFileStatus(fetchFileStatusUrl);
				break;
			case 7:
				HashMap<String, String> fileHashMap = new HashMap<>();
				
				fileHashMap.put("name", "file1.txt");
				fileHashMap.put("enlisted", "Completed");
				fileHashMap.put("extracted", "Not Completed");
				fileHashMap.put("inCleaning", "Not Completed");
				fileHashMap.put("cleaned", "Not Completed");
				fileHashMap.put("verified", "Completed");
				fileHashMap.put("mapped", "Completed");
				fileHashMap.put("inLoadtest", "Completed");
				fileHashMap.put("transferred", "Not Completed");
				fileHashMap.put("validated", "Completed");
				fileHashMap.put("inProd", "Not Completed");
				
				http.addFileStatus(addFileStatusUrl, fileHashMap);
				break;
			case 8:
				http.fetchObjectStatus(fetchObjectStatusUrl);
				break;
			case 9:
				HashMap<String, String> objectHashMap = new HashMap<>();
				
				objectHashMap.put("name", "object15");
				objectHashMap.put("enlisted", "Completed");
				objectHashMap.put("extracted", "Not Completed");
				objectHashMap.put("inCleaning", "Not Completed");
				objectHashMap.put("cleaned", "Not Completed");
				objectHashMap.put("verified", "Completed");
				objectHashMap.put("mapped", "Completed");
				objectHashMap.put("inLoadtest", "Completed");
				objectHashMap.put("transferred", "Not Completed");
				objectHashMap.put("validated", "Completed");
				objectHashMap.put("inProd", "Not Completed");
				
				http.addObjectStatus(addObjectStatusUrl, objectHashMap);
				break;
			case 10:
				ArrayList<HashMap<String,String>> list = new ArrayList<>();
				
				HashMap<String,String> jobMap1 = new HashMap<>();
				jobMap1.put("name", "Creating CAD Drawing");
				jobMap1.put("description", "Creation of CAD Drawing");
				jobMap1.put("startTime", "05/11/2019 13:16:12");
				jobMap1.put("endTime", "05/13/2019 22:43:17");
				jobMap1.put("jobType", "CAD Drawing");
				jobMap1.put("datasetSize", "3000");
				jobMap1.put("status", "Completed");
				jobMap1.put("hostname", "DS-INA745TJS6");
				jobMap1.put("environment", "DEV");
				jobMap1.put("processedCount", "2800");
				jobMap1.put("iteration", "1");
				jobMap1.put("validationPassed", "95");
				
				HashMap<String,String> jobMap2 = new HashMap<>();
				jobMap2.put("name", "Updating Document");
				jobMap2.put("description", "Updation of Document");
				jobMap2.put("startTime", "05/13/2019 13:16:12");
				jobMap2.put("endTime", "05/13/2019 22:43:17");
				jobMap2.put("jobType", "Document");
				jobMap2.put("datasetSize", "2000");
				jobMap2.put("status", "In Progress");
				jobMap2.put("hostname", "DS-INA745TJS6");
				jobMap2.put("environment", "DEV");
				jobMap2.put("processedCount", "1500");
				jobMap2.put("iteration", "2");
				jobMap2.put("validationPassed", "85");
				
				HashMap<String,String> jobMap3 = new HashMap<>();
				jobMap3.put("name", "Deleting Drawing");
				jobMap3.put("description", "Deletion of Drawing");
				jobMap3.put("startTime", "05/13/2019 15:32:55");
				jobMap3.put("endTime", "05/13/2019 16:52:16");
				jobMap3.put("jobType", "Drawing");
				jobMap3.put("datasetSize", "1000");
				jobMap3.put("status", "Completed");
				jobMap3.put("hostname", "DS-INA745TJS6");
				jobMap3.put("environment", "DEV");
				jobMap3.put("processedCount", "900");
				jobMap3.put("iteration", "3");
				jobMap3.put("validationPassed", "90");
				
				list.add(jobMap1);
				list.add(jobMap2);
				list.add(jobMap3);
				
				http.bulkloadJob(bulkCreateJobsUrl, list);
				break;
			case 11:
				ArrayList<HashMap<String,String>> fileList = new ArrayList<>();
				
				HashMap<String, String> fileHashMap1 = new HashMap<>();
				fileHashMap1.put("name", "file7.txt");
				fileHashMap1.put("enlisted", "Completed");
				fileHashMap1.put("extracted", "Not Completed");
				fileHashMap1.put("inCleaning", "Not Completed");
				fileHashMap1.put("cleaned", "Not Completed");
				fileHashMap1.put("verified", "Completed");
				fileHashMap1.put("mapped", "Completed");
				fileHashMap1.put("inLoadtest", "Completed");
				fileHashMap1.put("transferred", "Not Completed");
				fileHashMap1.put("validated", "Completed");
				fileHashMap1.put("inProd", "Not Completed");
				
				HashMap<String, String> fileHashMap2 = new HashMap<>();
				fileHashMap2.put("name", "file8.txt");
				fileHashMap2.put("enlisted", "Completed");
				fileHashMap2.put("extracted", "Not Completed");
				fileHashMap2.put("inCleaning", "Not Completed");
				fileHashMap2.put("cleaned", "Not Completed");
				fileHashMap2.put("verified", "Completed");
				fileHashMap2.put("mapped", "Completed");
				fileHashMap2.put("inLoadtest", "Completed");
				fileHashMap2.put("transferred", "Not Completed");
				fileHashMap2.put("validated", "Completed");
				fileHashMap2.put("inProd", "Not Completed");
				
				HashMap<String, String> fileHashMap3 = new HashMap<>();
				fileHashMap3.put("name", "file9.txt");
				fileHashMap3.put("enlisted", "Completed");
				fileHashMap3.put("extracted", "Not Completed");
				fileHashMap3.put("inCleaning", "Not Completed");
				fileHashMap3.put("cleaned", "Not Completed");
				fileHashMap3.put("verified", "Completed");
				fileHashMap3.put("mapped", "Completed");
				fileHashMap3.put("inLoadtest", "Completed");
				fileHashMap3.put("transferred", "Not Completed");
				fileHashMap3.put("validated", "Completed");
				fileHashMap3.put("inProd", "Not Completed");
				
				fileList.add(fileHashMap1);
				fileList.add(fileHashMap2);
				fileList.add(fileHashMap3);
				
				http.addBulkFileStatus(bulkAddFileStatusUrl, fileList);
				break;
			case 12:
				ArrayList<HashMap<String,String>> objectList = new ArrayList<>();
				
				HashMap<String, String> objectHashMap1 = new HashMap<>();
				objectHashMap1.put("name", "object7");
				objectHashMap1.put("enlisted", "Completed");
				objectHashMap1.put("extracted", "Not Completed");
				objectHashMap1.put("inCleaning", "Not Completed");
				objectHashMap1.put("cleaned", "Not Completed");
				objectHashMap1.put("verified", "Completed");
				objectHashMap1.put("mapped", "Completed");
				objectHashMap1.put("inLoadtest", "Completed");
				objectHashMap1.put("transferred", "Not Completed");
				objectHashMap1.put("validated", "Completed");
				objectHashMap1.put("inProd", "Not Completed");
				
				HashMap<String, String> objectHashMap2 = new HashMap<>();
				objectHashMap2.put("name", "object8");
				objectHashMap2.put("enlisted", "Completed");
				objectHashMap2.put("extracted", "Not Completed");
				objectHashMap2.put("inCleaning", "Not Completed");
				objectHashMap2.put("cleaned", "Not Completed");
				objectHashMap2.put("verified", "Completed");
				objectHashMap2.put("mapped", "Completed");
				objectHashMap2.put("inLoadtest", "Completed");
				objectHashMap2.put("transferred", "Not Completed");
				objectHashMap2.put("validated", "Completed");
				objectHashMap2.put("inProd", "Not Completed");
				
				HashMap<String, String> objectHashMap3 = new HashMap<>();
				objectHashMap3.put("name", "object9");
				objectHashMap3.put("enlisted", "Completed");
				objectHashMap3.put("extracted", "Not Completed");
				objectHashMap3.put("inCleaning", "Not Completed");
				objectHashMap3.put("cleaned", "Not Completed");
				objectHashMap3.put("verified", "Completed");
				objectHashMap3.put("mapped", "Completed");
				objectHashMap3.put("inLoadtest", "Completed");
				objectHashMap3.put("transferred", "Not Completed");
				objectHashMap3.put("validated", "Completed");
				objectHashMap3.put("inProd", "Not Completed");
				
				objectList.add(objectHashMap1);
				objectList.add(objectHashMap2);
				objectList.add(objectHashMap3);
				
				http.addBulkObjectStatus(blukAddObjectStatusUrl, objectList);
				break;
			case 13:
				ArrayList<HashMap<String, String>> jobUpdateList = new ArrayList<>();
				
				HashMap<String, String> updateJobMap1 = new HashMap<>();
				updateJobMap1.put("jobTicket", "-1mcmpl4d24jwswm7as");
				updateJobMap1.put("endTime", "05/15/2019 18:27:17");
				updateJobMap1.put("datasetSize", "3500");
				updateJobMap1.put("processedCount", "2495");
				updateJobMap1.put("validationPassed", "99");
				updateJobMap1.put("status", "In Progress");
				
//				HashMap<String, String> updateJobMap2 = new HashMap<>();
//				updateJobMap2.put("jobTicket", "Part-1mcmpl4g7wjvp69d1t");
//				updateJobMap2.put("endTime", "05/15/2019 18:27:17");
//				updateJobMap2.put("datasetSize", "3500");
//				updateJobMap2.put("processedCount", "2495");
//				updateJobMap2.put("validationPassed", "99");
//				updateJobMap2.put("status", "In Progress");
//				
//				HashMap<String, String> updateJobMap3 = new HashMap<>();
//				updateJobMap3.put("jobTicket", "Document-1mcmpl4g7wjvp69d1y");
//				updateJobMap3.put("endTime", "05/15/2019 18:27:17");
//				updateJobMap3.put("datasetSize", "3500");
//				updateJobMap3.put("processedCount", "2495");
//				updateJobMap3.put("validationPassed", "99");
//				updateJobMap3.put("status", "Abandoned");
				
				jobUpdateList.add(updateJobMap1);
//				jobUpdateList.add(updateJobMap2);
//				jobUpdateList.add(updateJobMap3);
				
				http.updateBulkJob(bulkUpdateJobsUrl, jobUpdateList);
				break;
			case 14:
				ArrayList<HashMap<String,String>> updateFileList = new ArrayList<>();
				
				HashMap<String, String> updateFileHashMap1 = new HashMap<>();
				updateFileHashMap1.put("name", "file7.txt");
				updateFileHashMap1.put("enlisted", "Completed");
				updateFileHashMap1.put("extracted", "Not Completed");
				updateFileHashMap1.put("inCleaning", "Not Completed");
				updateFileHashMap1.put("cleaned", "Not Completed");
				updateFileHashMap1.put("verified", "Completed");
				updateFileHashMap1.put("mapped", "Completed");
				updateFileHashMap1.put("inLoadtest", "Completed");
				updateFileHashMap1.put("transferred", "Not Completed");
				updateFileHashMap1.put("validated", "Completed");
				updateFileHashMap1.put("inProd", "Completed");
				
				HashMap<String, String> updateFileHashMap2 = new HashMap<>();
				updateFileHashMap2.put("name", "file8.txt");
				updateFileHashMap2.put("enlisted", "Completed");
				updateFileHashMap2.put("extracted", "Not Completed");
				updateFileHashMap2.put("inCleaning", "Not Completed");
				updateFileHashMap2.put("cleaned", "Not Completed");
				updateFileHashMap2.put("verified", "Completed");
				updateFileHashMap2.put("mapped", "Completed");
				updateFileHashMap2.put("inLoadtest", "Completed");
				updateFileHashMap2.put("transferred", "Not Completed");
				updateFileHashMap2.put("validated", "Completed");
				updateFileHashMap2.put("inProd", "Completed");
				
				HashMap<String, String> updateFileHashMap3 = new HashMap<>();
				updateFileHashMap3.put("name", "file9.txt");
				updateFileHashMap3.put("enlisted", "Completed");
				updateFileHashMap3.put("extracted", "Not Completed");
				updateFileHashMap3.put("inCleaning", "Not Completed");
				updateFileHashMap3.put("cleaned", "Not Completed");
				updateFileHashMap3.put("verified", "Completed");
				updateFileHashMap3.put("mapped", "Completed");
				updateFileHashMap3.put("inLoadtest", "Completed");
				updateFileHashMap3.put("transferred", "Not Completed");
				updateFileHashMap3.put("validated", "Completed");
				updateFileHashMap3.put("inProd", "Completed");
				
				updateFileList.add(updateFileHashMap1);
				updateFileList.add(updateFileHashMap2);
				updateFileList.add(updateFileHashMap3);
				http.UpdateBulkFileStatus(bulkUpdateFileStatusUrl, updateFileList);
				break;
			case 15:
				ArrayList<HashMap<String,String>> updateObjectList = new ArrayList<>();
				
				HashMap<String, String> updateObjectHashMap1 = new HashMap<>();
				updateObjectHashMap1.put("name", "object7");
				updateObjectHashMap1.put("enlisted", "Completed");
				updateObjectHashMap1.put("extracted", "Not Completed");
				updateObjectHashMap1.put("inCleaning", "Not Completed");
				updateObjectHashMap1.put("cleaned", "Not Completed");
				updateObjectHashMap1.put("verified", "Completed");
				updateObjectHashMap1.put("mapped", "Completed");
				updateObjectHashMap1.put("inLoadtest", "Completed");
				updateObjectHashMap1.put("transferred", "Not Completed");
				updateObjectHashMap1.put("validated", "Completed");
				updateObjectHashMap1.put("inProd", "Completed");
				
				HashMap<String, String> updateObjectHashMap2 = new HashMap<>();
				updateObjectHashMap2.put("name", "object8");
				updateObjectHashMap2.put("enlisted", "Completed");
				updateObjectHashMap2.put("extracted", "Not Completed");
				updateObjectHashMap2.put("inCleaning", "Not Completed");
				updateObjectHashMap2.put("cleaned", "Not Completed");
				updateObjectHashMap2.put("verified", "Completed");
				updateObjectHashMap2.put("mapped", "Completed");
				updateObjectHashMap2.put("inLoadtest", "Completed");
				updateObjectHashMap2.put("transferred", "Not Completed");
				updateObjectHashMap2.put("validated", "Completed");
				updateObjectHashMap2.put("inProd", "Completed");
				
				HashMap<String, String> updateObjectHashMap3 = new HashMap<>();
				updateObjectHashMap3.put("name", "object9");
				updateObjectHashMap3.put("enlisted", "Completed");
				updateObjectHashMap3.put("extracted", "Not Completed");
				updateObjectHashMap3.put("inCleaning", "Not Completed");
				updateObjectHashMap3.put("cleaned", "Not Completed");
				updateObjectHashMap3.put("verified", "Completed");
				updateObjectHashMap3.put("mapped", "Completed");
				updateObjectHashMap3.put("inLoadtest", "Completed");
				updateObjectHashMap3.put("transferred", "Not Completed");
				updateObjectHashMap3.put("validated", "Completed");
				updateObjectHashMap3.put("inProd", "Completed");
				
				updateObjectList.add(updateObjectHashMap1);
				updateObjectList.add(updateObjectHashMap2);
				updateObjectList.add(updateObjectHashMap3);
				http.UpdateBulkObjectStatus(bulkUpdateObjectStatusUrl, updateObjectList);
				break;
			case 16:
				condition = false;
				break;
			default:
				System.out.println("Invalid Option!");
				break;
			}
		}

	}
	
	/**
	 * This method is used to fetch the jobs that is stored in the mean stack server.
	 * @param url This is the URL provided for fetching the jobs (Mandatory, non null, non empty)
	 * @param fromYear This is the year of the from date (Mandatory, non null, non empty)
	 * @param fromMonth This is the month of the from date (Mandatory, non null, non empty)
	 * @param fromDay This is the day of the from date (Mandatory, non null, non empty)
	 * @param toYear This is the year of the to date (Mandatory, non null, non empty)
	 * @param toMonth This is the month of the to date (Mandatory, non null, non empty)
	 * @param toDay This is the day of the to date (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully fetched the jobs else it returns false 
	 * @throws Exception Handles all the exceptions
	 */
	public boolean fetchJobs(String url, String fromYear, String fromMonth, String fromDay, String toYear, String toMonth, String toDay) throws Exception {
		url += "?fromYear="+fromYear+"&fromMonth="+fromMonth+"&fromDay="+fromDay+"&toYear="+toYear+"&toMonth="+toMonth+"&toDay="+toDay;
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		
		boolean outcome = false;
		try {
			if(fromYear == null || fromMonth == null || fromDay == null || toYear == null || toMonth == null || toDay == null) {
				throw new RuntimeException("Date values can not be taken as null!");
			}
			
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpGet request1 = new HttpGet(url);
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
				            
			            JSONObject jsonObj = new JSONObject(output);
			            String message = jsonObj.getString("message");
			            System.out.println("Message : "+ message);
			            
			            JSONArray arrObj = jsonObj.getJSONArray("data");
			            
			            for(int i = 0; i < arrObj.length(); i++) {
			            	System.out.println(arrObj.getJSONObject(i));
			            }
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
		    return outcome;

		    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				outcome = false;
				return outcome;
				
			} finally {
				httpclient.close();
				
			}
		
	}
	

	/**
	 * This method is used to add bulk jobs to the mean stack server.
	 * @param url This is the URL to add bulk of jobs to mean stack server (Mandatory, non null, non empty)
	 * @param list containing a list of hash maps representing jobs (Mandatory, non null, non empty)
	 * @return boolean It return true is successfully added the jobs else returns false
	 * @throws Exception Handles all the exception
	 */
	public boolean bulkloadJob(String url, ArrayList<HashMap<String, String>> list) throws Exception
	{
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			httpclient.start();
			final CountDownLatch latch1 = new CountDownLatch(1);
			
			for(HashMap<String, String> hashmap : list) {
				for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
				    if(entry.getKey() == null || entry.getValue() == null) {
				    	throw new RuntimeException("Job detail values can not be taken as null!");
				    }
				}
			}
			
			Job job = new Job();
			List<Job> bulkJobs = new ArrayList<Job>();
			
			for(HashMap<String, String> hashmap : list) {
				Job newJob = new Job((String)hashmap.get("name"), (String)hashmap.get("description"), (String)hashmap.get("startTime"), (String)hashmap.get("endTime"), (String)hashmap.get("jobType"), Integer.parseInt((String)hashmap.get("datasetSize")), (String)hashmap.get("status"), (String)hashmap.get("hostname"), (String)hashmap.get("environment"), Integer.parseInt((String)hashmap.get("processedCount")), Integer.parseInt((String)hashmap.get("iteration")), Integer.parseInt((String)hashmap.get("validationPassed")));
				bulkJobs.add(newJob);
			}
		    
		    HttpPost request1 = new HttpPost(url);

		    
		    StringEntity input = new StringEntity(job.toJsonString(bulkJobs));
		    input.setContentType("application/json");
			request1.setEntity(input);
			System.out.println(" Execution of the bulk jobs post req " + job.toJsonString(bulkJobs));
			Future<HttpResponse> response =  httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e+"23");
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
			
			latch1.await();
			outcome = true;
			return outcome;
			}
			catch(Exception e) {
				System.out.println(e+"6t");
				outcome = false;
				return outcome;
			}
			finally {
				httpclient.close();
			}
	}
	
	/**
	 * This method is used to add a single job to the mean stack server.
	 * @param url This is the URL to add a single job to the mean stack server (Mandatory, non null, non empty)
	 * @param map containing a hash map representing a job (Mandatory, non null, non empty)
	 * @return boolean return true is successfully added the job else returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean addJobs(String url, HashMap<String,String> map) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
			    if(entry.getKey() == null || entry.getValue() == null) {
			    	throw new RuntimeException("Job detail values can not be taken as null!");
			    }
			}
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue() ));
			}

		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpPost request1 = new HttpPost(url);
			
			request1.setEntity(new UrlEncodedFormEntity(urlParameters));
			
			System.out.println(" Execution of the jobs post req " + urlParameters);
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine()+ "SOP called");
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex+" Failed");
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used to fetch all the progress stored in the mean stack server.
	 * @param url This is the URL used to fetch the progress from the mean stack server (Mandatory, non null, non empty)
	 * @param fromYear This is the year of the from date (Mandatory, non null, non empty)
	 * @param fromMonth This is the month of the from date (Mandatory, non null, non empty)
	 * @param fromDay This is the day of the from date (Mandatory, non null, non empty)
	 * @param toYear This is the year of the to date (Mandatory, non null, non empty)
	 * @param toMonth This is the month of the to date (Mandatory, non null, non empty)
	 * @param toDay This is the day of the to date (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully fetched the progress else returns false
	 * @throws Exception Handle all the exceptions
	 */
	public boolean fetchProgress(String url, String fromYear, String fromMonth, String fromDay, String toYear, String toMonth, String toDay) throws Exception {
		url += "?fromYear="+fromYear+"&fromMonth="+fromMonth+"&fromDay="+fromDay+"&toYear="+toYear+"&toMonth="+toMonth+"&toDay="+toDay;
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			
			if(fromYear == null || fromMonth == null || fromDay == null || toYear == null || toMonth == null || toDay == null) {
				throw new RuntimeException("Date values can not be taken as null!");
			}
			
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpGet request1 = new HttpGet(url);
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
				            
			            JSONObject jsonObj = new JSONObject(output);
			            String message = jsonObj.getString("message");
			            System.out.println("Message : "+ message);
			            
			            JSONArray arrObj = jsonObj.getJSONArray("data");
			            
			            for(int i = 0; i < arrObj.length(); i++) {
			            	System.out.println(arrObj.getJSONObject(i));
			            	System.out.println(arrObj.getJSONObject(i).getString("jobTicket"));
			            }
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This is a method used to fetch the assessments stored in the mean stack server.
	 * @param url This is the URL used to fetch the assessments from the mean stack server (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully fetched the assessments else it return false
	 * @throws Exception It handle all the exceptions
	 */
	public boolean fetchAssessments(String url) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpGet request1 = new HttpGet(url);
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
				            
			            JSONObject jsonObj = new JSONObject(output);
			            String message = jsonObj.getString("message");
			            System.out.println("Message : "+ message);
			            
			            JSONArray arrObj = jsonObj.getJSONArray("data");
			            
			            for(int i = 0; i < arrObj.length(); i++) {
			            	System.out.println(arrObj.getJSONObject(i));
			            }
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used to add the assessments to the mean stack server.
	 * @param url This is the URL used to add the assessments to the mean stack server (Mandatory, non null, non empty)
	 * @param hashmap This is a hash map containing the type and its count (Mandatory, non null, non empty)
	 * @return boolean It returns true on successfully addition of assessment to the mean stack server
	 * @throws Exception Handle all the exceptions
	 */
	public boolean addAssessment(String url, HashMap<String, Integer> hashmap) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			if(hashmap == null) {
				throw new RuntimeException("Assessment detail values can not be taken as null!");
			}
			
			String typeCountValue = "";
			
			for (HashMap.Entry<String, Integer> entry : hashmap.entrySet()) {
				typeCountValue += entry.getKey()+"_"+(entry.getValue()).toString()+"|";
			}
			
			typeCountValue = typeCountValue.substring(0, typeCountValue.length() - 1);
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			
			urlParameters.add(new BasicNameValuePair("type_count", typeCountValue));

			
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpPost request1 = new HttpPost(url);
			
			request1.setEntity(new UrlEncodedFormEntity(urlParameters));
		    
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used to fetch all the files status from the mean stack server
	 * @param url This is the URL used to fetch the file status from the mean stack server (Mandatory, non null, non empty)
	 * @return boolean It returns true is successfully fetched the data else return false
	 * @throws Exception Handle all the exceptions
	 */
	public boolean fetchFileStatus(String url) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpGet request1 = new HttpGet(url);
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
				            
			            JSONObject jsonObj = new JSONObject(output);
			            String message = jsonObj.getString("message");
			            System.out.println("Message : "+ message);
			            
			            JSONArray arrObj = jsonObj.getJSONArray("data");
			            
			            for(int i = 0; i < arrObj.length(); i++) {
			            	System.out.println(arrObj.getJSONObject(i));
			            }
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used the add the file status to the mean stack server.
	 * @param url This is the URL used to add the file status to the mean stack server (Mandatory, non null, non empty)
	 * @param map This is a hash map representing file status (Mandatory, non null, non empty)
	 * @return boolean It returns true is successfully added the data else returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean addFileStatus(String url, HashMap<String, String> map) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
			    if(entry.getKey() == null || entry.getValue() == null) {
			    	throw new RuntimeException("File Status detail values can not be taken as null!");
			    }
			}
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpPost request1 = new HttpPost(url);
			
			request1.setEntity(new UrlEncodedFormEntity(urlParameters));
		    
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used to add bulk file status to the mean stack server.
	 * @param url This is the URL used to add the bulk file status to the mean stack server (Mandatory, non null, non empty)
	 * @param list This is a list containing hash maps representing file status (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully added the data else returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean addBulkFileStatus(String url, ArrayList<HashMap<String, String>> list) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			httpclient.start();
			final CountDownLatch latch1 = new CountDownLatch(1);
			
			for(HashMap<String, String> hashmap : list) {
				for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
				    if(entry.getKey() == null || entry.getValue() == null) {
				    	throw new RuntimeException("File status detail values can not be taken as null!");
				    }
				}
			}
			
			FileStatus fileStatus = new FileStatus();
			List<FileStatus> bulkFileStatus = new ArrayList<FileStatus>();
			
			for(HashMap<String, String> hashmap : list) {
				FileStatus newFileStatus = new FileStatus((String)hashmap.get("name"),
						(String)hashmap.get("enlisted"),(String)hashmap.get("extracted"),
						(String)hashmap.get("inCleaning"),(String)hashmap.get("cleaned"),
						(String)hashmap.get("verified"),(String)hashmap.get("mapped"),
						(String)hashmap.get("inLoadtest"),(String)hashmap.get("transferred"),
						(String)hashmap.get("validated"),(String)hashmap.get("inProd"));
				bulkFileStatus.add(newFileStatus);
			}
		    
		    HttpPost request1 = new HttpPost(url);

		    
		    StringEntity input = new StringEntity(fileStatus.toJsonString(bulkFileStatus));
		    input.setContentType("application/json");
			request1.setEntity(input);
			System.out.println(" Execution of the post req " + fileStatus.toJsonString(bulkFileStatus));
			Future<HttpResponse> response =  httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
			
			latch1.await();
			outcome = true;
			return outcome;
			}
			catch(Exception e) {
				outcome = false;
				return outcome;
			}
			finally {
				httpclient.close();
			}
	}
	
	/**
	 * This method is used to fetch the object status from the mean stack server
	 * @param url This is the URL used to fetch the object status from the mean stack server (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully added the data else it returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean fetchObjectStatus(String url) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpGet request1 = new HttpGet(url);
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
				            
			            JSONObject jsonObj = new JSONObject(output);
			            String message = jsonObj.getString("message");
			            System.out.println("Message : "+ message);
			            
			            JSONArray arrObj = jsonObj.getJSONArray("data");
			            
			            for(int i = 0; i < arrObj.length(); i++) {
			            	System.out.println(arrObj.getJSONObject(i));
			            }
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used to add bulk object status to the mean stack server.
	 * @param url This is the URL used to add the object status to the mean stack server (Mandatory, non null, non empty)
	 * @param map This is a hash map representing object status (Mandatory, non null, non empty)
	 * @return boolean It returns true is successfully added the data else returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean addObjectStatus(String url, HashMap<String, String> map) throws Exception {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {			
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
			    if(entry.getKey() == null || entry.getValue() == null) {
			    	throw new RuntimeException("Object Status detail values can not be taken as null!");
			    }
			}
			
			List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
			
			for (HashMap.Entry<String, String> entry : map.entrySet()) {
				urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			
		    // Start the client
		    httpclient.start();
		    final CountDownLatch latch1 = new CountDownLatch(1);

		    // Execute request
		    final HttpPost request1 = new HttpPost(url);
		    
		    
			
			request1.setEntity(new UrlEncodedFormEntity(urlParameters));
		    
		    Future<HttpResponse> future = httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
				            
			            JSONObject jsonObj = new JSONObject(output);
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
		    latch1.await();
		    outcome = true;
			return outcome;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				outcome = false;
				return outcome;
			} finally {
				httpclient.close();
			}
		
	}
	
	/**
	 * This method is used to add bulk object status to the mean stack server.
	 * @param url This URL is used to add the bulk object status to the mean stack server (Mandatory, non null, non empty)
	 * @param list This is a list of hash maps representing object status (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully added the data else it returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean addBulkObjectStatus(String url, ArrayList<HashMap<String, String>> list) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			httpclient.start();
			final CountDownLatch latch1 = new CountDownLatch(1);
			
			for(HashMap<String, String> hashmap : list) {
				for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
				    if(entry.getKey() == null || entry.getValue() == null) {
				    	throw new RuntimeException("Object status detail values can not be taken as null!");
				    }
				}
			}
			
			ObjectStatus objectStatus = new ObjectStatus();
			List<ObjectStatus> bulkObjectStatus = new ArrayList<ObjectStatus>();
			
			for(HashMap<String, String> hashmap : list) {
				ObjectStatus newObjectStatus = new ObjectStatus((String)hashmap.get("name"),
						(String)hashmap.get("enlisted"),(String)hashmap.get("extracted"),
						(String)hashmap.get("inCleaning"),(String)hashmap.get("cleaned"),
						(String)hashmap.get("verified"),(String)hashmap.get("mapped"),
						(String)hashmap.get("inLoadtest"),(String)hashmap.get("transferred"),
						(String)hashmap.get("validated"),(String)hashmap.get("inProd"));
				bulkObjectStatus.add(newObjectStatus);
			}
		    
		    HttpPost request1 = new HttpPost(url);
		    
		    StringEntity input = new StringEntity(objectStatus.toJsonString(bulkObjectStatus));
		    input.setContentType("application/json");
			request1.setEntity(input);
			System.out.println(" Execution of the post req " + objectStatus.toJsonString(bulkObjectStatus));
			Future<HttpResponse> response =  httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
			
			latch1.await();
			outcome = true;
			return outcome;
			}
			catch(Exception e) {
				outcome = false;
				return outcome;
			}
			finally {
				httpclient.close();
			}
	}
	
	/**
	 * This method is used to update bulk jobs to the mean stack server.
	 * @param url This is the URL used to update the bulk jobs to the mean stack server (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully updated the jobs else returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean updateBulkJob(String url, ArrayList<HashMap<String, String>> list) throws Exception
	{
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			httpclient.start();
			final CountDownLatch latch1 = new CountDownLatch(1);
			
			
			for(HashMap<String, String> hashmap : list) {
				for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
				    if(entry.getKey() == null || entry.getValue() == null) {
				    	throw new RuntimeException("Object status detail values can not be taken as null!");
				    }
				}
			}
			UpdateJob job = new UpdateJob();
			List<UpdateJob> bulkJobs = new ArrayList<UpdateJob>();
			for(HashMap<String, String> hashmap : list) {
				UpdateJob newJob = new UpdateJob((String)hashmap.get("jobTicket"), (String)hashmap.get("endTime"), Integer.parseInt((String)hashmap.get("datasetSize")), Integer.parseInt((String)hashmap.get("processedCount")), Integer.parseInt((String)hashmap.get("validationPassed")), (String)hashmap.get("status"));
				bulkJobs.add(newJob);
			}
			HttpPut request1 = new HttpPut(url);

		    StringEntity input = new StringEntity(job.toJsonString(bulkJobs));
		    input.setContentType("application/json");
			request1.setEntity(input);
			System.out.println(" Execution of the put req " + job.toJsonString(bulkJobs));
			Future<HttpResponse> response =  httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex+"failed");
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
			
			latch1.await();
			outcome = true;
			return outcome;
			}
			catch(Exception e) {
				System.out.println(e);
				outcome = false;
				return outcome;
			}
			finally {
				httpclient.close();
			}
	}
	
	/**
	 * This method is used to update bulk file status to the mean stack server.
	 * @param url This is the URL used to update the bulk file status to the mean stack server (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully added the data else returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean UpdateBulkFileStatus(String url, ArrayList<HashMap<String, String>> list) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			httpclient.start();
			final CountDownLatch latch1 = new CountDownLatch(1);
			
			for(HashMap<String, String> hashmap : list) {
				for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
				    if(entry.getKey() == null || entry.getValue() == null) {
				    	throw new RuntimeException("File status detail values can not be taken as null!");
				    }
				}
			}
			
			FileStatus fileStatus = new FileStatus();
			List<FileStatus> bulkFileStatus = new ArrayList<FileStatus>();
			
			for(HashMap<String, String> hashmap : list) {
				FileStatus newFileStatus = new FileStatus((String)hashmap.get("name"),
						(String)hashmap.get("enlisted"),(String)hashmap.get("extracted"),
						(String)hashmap.get("inCleaning"),(String)hashmap.get("cleaned"),
						(String)hashmap.get("verified"),(String)hashmap.get("mapped"),
						(String)hashmap.get("inLoadtest"),(String)hashmap.get("transferred"),
						(String)hashmap.get("validated"),(String)hashmap.get("inProd"));
				bulkFileStatus.add(newFileStatus);
			}
		    
			HttpPut request1 = new HttpPut(url);

		    
		    StringEntity input = new StringEntity(fileStatus.toJsonString(bulkFileStatus));
		    input.setContentType("application/json");
			request1.setEntity(input);
			System.out.println(" Execution of the put req " + fileStatus.toJsonString(bulkFileStatus));
			Future<HttpResponse> response =  httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex+"failed");
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
			
			latch1.await();
			outcome = true;
			return outcome;
			}
			catch(Exception e) {
				outcome = false;
				return outcome;
			}
			finally {
				httpclient.close();
			}
	}
	
	/**
	 * This method is used to update bulk object status to the mean stack server.
	 * @param url This URL is used to update the bulk object status to the mean stack server (Mandatory, non null, non empty)
	 * @return boolean It returns true if successfully added the data else it returns false
	 * @throws Exception Handles all the exceptions
	 */
	public boolean UpdateBulkObjectStatus(String url, ArrayList<HashMap<String, String>> list) throws Exception {
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
		boolean outcome = false;
		try {
			httpclient.start();
			final CountDownLatch latch1 = new CountDownLatch(1);
			
			for(HashMap<String, String> hashmap : list) {
				for (HashMap.Entry<String, String> entry : hashmap.entrySet()) {
				    if(entry.getKey() == null || entry.getValue() == null) {
				    	throw new RuntimeException("Object status detail values can not be taken as null!");
				    }
				}
			}
			
			ObjectStatus objectStatus = new ObjectStatus();
			List<ObjectStatus> bulkObjectStatus = new ArrayList<ObjectStatus>();
			
			for(HashMap<String, String> hashmap : list) {
				ObjectStatus newObjectStatus = new ObjectStatus((String)hashmap.get("name"),
						(String)hashmap.get("enlisted"),(String)hashmap.get("extracted"),
						(String)hashmap.get("inCleaning"),(String)hashmap.get("cleaned"),
						(String)hashmap.get("verified"),(String)hashmap.get("mapped"),
						(String)hashmap.get("inLoadtest"),(String)hashmap.get("transferred"),
						(String)hashmap.get("validated"),(String)hashmap.get("inProd"));
				bulkObjectStatus.add(newObjectStatus);
			}
		    
		    HttpPut request1 = new HttpPut(url);

		    
		    StringEntity input = new StringEntity(objectStatus.toJsonString(bulkObjectStatus));
		    input.setContentType("application/json");
			request1.setEntity(input);
			System.out.println(" Execution of the post req " + objectStatus.toJsonString(bulkObjectStatus));
			Future<HttpResponse> response =  httpclient.execute(request1, new FutureCallback<HttpResponse>() {
 
		        public void completed(final HttpResponse response1) {
		        	latch1.countDown();

		            System.out.println(request1.getRequestLine() + "->" + response1.getStatusLine());
		            try {
		            	BufferedReader br;
						br = new BufferedReader(new InputStreamReader((response1.getEntity().getContent())));
						
						String output;
			            System.out.println("Output from Server .... \n");
						output = br.readLine();
							
						System.out.println(output);
				        System.out.println("-----------------------------------");
			            
		            	} catch(Exception e) {
		            		System.out.println(e);
		            	}
		            
		        }

		        public void failed(final Exception ex) {
		            System.out.println(request1.getRequestLine() + "->" + ex);
		        }

		        public void cancelled() {
		            System.out.println(request1.getRequestLine() + " cancelled");
		        }
		    });
			
			latch1.await();
			outcome = true;
			return outcome;
			}
			catch(Exception e) {
				outcome = false;
				return outcome;
			}
			finally {
				httpclient.close();
			}
	}

}

