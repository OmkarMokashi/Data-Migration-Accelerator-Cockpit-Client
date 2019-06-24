package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import restclient.CockPitRestClient;

public class TestRestClient {
	
	private CockPitRestClient cockPitRestClient = null;
	
	
	private static String fetchJobsUrl = "";
	private static String addJobsUrl = "";
	private static String fetchProgressUrl = "";
	private static String fetchAssessmentUrl = "";
	private static String addAssessmentUrl = "";
	private static String fetchFileStatusUrl = "";
	private static String addFileStatusUrl = "";
	private static String fetchObjectStatusUrl = "";
	private static String addObjectStatusUrl = "";
	private static String bulkCreateJobsUrl = "";
	private static String bulkAddFileStatusUrl = "";
	private static String blukAddObjectStatusUrl = "";
	private static String bulkUpdateJobsUrl = "";
	private static String bulkUpdateFileStatusUrl = "";
	private static String bulkUpdateObjectStatusUrl = "";

	@Before
	public void setUp() throws Exception {
		
		Properties prop = new Properties();
		InputStream input = new FileInputStream("restclient.properties");
		prop.load(input);
		
		fetchJobsUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/fetchJobs";
		addJobsUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/createJob";
		fetchProgressUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/fetchProgress";
		fetchAssessmentUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/fetchAssessments";
		addAssessmentUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/addAssessment";
		fetchFileStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/fetchFileStatus";
		addFileStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/addFileStatus";
		fetchObjectStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/fetchObjectStatus";
		addObjectStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/addObjectStatus";
		bulkCreateJobsUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/addBulkJobs";
		bulkAddFileStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/addBulkFileStatus";
		blukAddObjectStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/addBulkObjectStatus";
		bulkUpdateJobsUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/api/bulkUpdateJobs";
		bulkUpdateFileStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/api/bulkUpdateFileStatus";
		bulkUpdateObjectStatusUrl += prop.getProperty("protocol")+"://"+prop.getProperty("host")+":"+prop.getProperty("port")+"/api/bulkUpdateObjectStatus";
		
		System.out.println(prop.getProperty("protocol"));
		System.out.println(prop.getProperty("host"));
		System.out.println(prop.getProperty("port"));
		
		this.cockPitRestClient = new CockPitRestClient();
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFetchJobs() throws Exception {
		boolean outcome = this.cockPitRestClient.fetchJobs(fetchJobsUrl, "2019", "3", "4", "2019", "4", "4");
		Assert.assertTrue(outcome);
		
	}

	@Test
	public void testBulkloadJob() throws Exception {
		ArrayList<HashMap<String,String>> list = new ArrayList<>();
		
		HashMap<String,String> map1 = new HashMap<>();
		map1.put("name", "Updating Drawing");
		map1.put("description", "Updation of Drawing");
		map1.put("startTime", "05/12/2018 13:16:12");
		map1.put("endTime", "05/13/2018 22:43:17");
		map1.put("jobType", "Drawing");
		map1.put("datasetSize", "2500");
		map1.put("status", "Completed");
		map1.put("hostname", "DS-INA745TJS6");
		map1.put("environment", "DEV");
		map1.put("processedCount", "2400");
		map1.put("iteration", "4");
		map1.put("validationPassed", "90");
		
		HashMap<String,String> map2 = new HashMap<>();
		map2.put("name", "Updating Drawing");
		map2.put("description", "Updation of Drawing");
		map2.put("startTime", "05/12/2018 13:16:12");
		map2.put("endTime", "05/13/2018 22:43:17");
		map2.put("jobType", "Drawing");
		map2.put("datasetSize", "2500");
		map2.put("status", "Completed");
		map2.put("hostname", "DS-INA745TJS6");
		map2.put("environment", "DEV");
		map2.put("processedCount", "2400");
		map2.put("iteration", "4");
		map2.put("validationPassed", "90");
		
		HashMap<String,String> map3 = new HashMap<>();
		map3.put("name", "Updating Drawing");
		map3.put("description", "Updation of Drawing");
		map3.put("startTime", "05/12/2018 13:16:12");
		map3.put("endTime", "05/13/2018 22:43:17");
		map3.put("jobType", "Drawing");
		map3.put("datasetSize", "2500");
		map3.put("status", "Completed");
		map3.put("hostname", "DS-INA745TJS6");
		map3.put("environment", "DEV");
		map3.put("processedCount", "2400");
		map3.put("iteration", "4");
		map3.put("validationPassed", "90");
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		boolean outcome = this.cockPitRestClient.bulkloadJob(bulkCreateJobsUrl, list);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testAddJobs() throws Exception {
		HashMap<String,String> map = new HashMap<>();
		map.put("name", "Updating Drawing");
		map.put("description", "Updation of Drawing");
		map.put("startTime", "03/24/2018 13:16:12");
		map.put("endTime", "03/24/2018 22:43:17");
		map.put("jobType", "Document");
		map.put("datasetSize", "2500");
		map.put("status", "Completed");
		map.put("hostname", "DS-INA745TJS6");
		map.put("environment", "DEV");
		map.put("processedCount", "2400");
		map.put("iteration", "4");
		map.put("validationPassed", "90");
		boolean outcome = this.cockPitRestClient.addJobs(addJobsUrl, map);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testFetchProgress() throws Exception {
		boolean outcome = this.cockPitRestClient.fetchProgress(fetchProgressUrl, "2019", "3", "4", "2019", "4", "4");
		Assert.assertTrue(outcome);
	}

	@Test
	public void testFetchAssessments() throws Exception {
		boolean outcome = this.cockPitRestClient.fetchAssessments(fetchAssessmentUrl);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testAddAssessment() throws Exception {
		HashMap<String, Integer> assessmentMap = new HashMap<>();
		assessmentMap.put("Part", 12000);
		assessmentMap.put("Drawing", 10000);
		assessmentMap.put("EBOM", 14000);
		assessmentMap.put("MBOM", 9000);
		assessmentMap.put("Documents", 8000);
		assessmentMap.put("Part Specification", 5000);
		assessmentMap.put("Change Request", 6000);
		assessmentMap.put("Change Order", 8000);
		boolean outcome = this.cockPitRestClient.addAssessment(addAssessmentUrl, assessmentMap);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testFetchFileStatus() throws Exception {
		boolean outcome = this.cockPitRestClient.fetchFileStatus(fetchFileStatusUrl);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testAddFileStatus() throws Exception {
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
		boolean outcome = this.cockPitRestClient.addFileStatus(addFileStatusUrl, fileHashMap);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testAddBulkFileStatus() throws Exception {
		ArrayList<HashMap<String,String>> fileList = new ArrayList<>();
		
		HashMap<String, String> fileHashMap1 = new HashMap<>();
		fileHashMap1.put("name", "file1.txt");
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
		fileHashMap2.put("name", "file1.txt");
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
		fileHashMap3.put("name", "file1.txt");
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
		boolean outcome = this.cockPitRestClient.addBulkFileStatus(bulkAddFileStatusUrl, fileList);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testFetchObjectStatus() throws Exception {
		boolean outcome = this.cockPitRestClient.fetchObjectStatus(fetchObjectStatusUrl);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testAddObjectStatus() throws Exception {
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
		boolean outcome = this.cockPitRestClient.addObjectStatus(addObjectStatusUrl, objectHashMap);
		Assert.assertTrue(outcome);
	}

	@Test
	public void testAddBulkObjectStatus() throws Exception {
		ArrayList<HashMap<String,String>> objectList = new ArrayList<>();
		
		HashMap<String, String> objectHashMap1 = new HashMap<>();
		objectHashMap1.put("name", "file1.txt");
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
		objectHashMap2.put("name", "file1.txt");
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
		objectHashMap3.put("name", "file1.txt");
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
		boolean outcome = this.cockPitRestClient.addBulkObjectStatus(blukAddObjectStatusUrl, objectList);
		Assert.assertTrue(outcome);
	}
	
	@Test
	public void testUpdateBulkJob() throws Exception {
		ArrayList<HashMap<String, String>> jobUpdateList = new ArrayList<>();
		
		HashMap<String, String> updateJobMap1 = new HashMap<>();
		updateJobMap1.put("jobTicket", "Drawing-1mcmpl412wjvnsbkqs");
		updateJobMap1.put("endTime", "03/27/2018 18:27:17");
		updateJobMap1.put("datasetSize", "3500");
		updateJobMap1.put("processedCount", "2495");
		updateJobMap1.put("validationPassed", "99");
		updateJobMap1.put("status", "In Progress");
		
		HashMap<String, String> updateJobMap2 = new HashMap<>();
		updateJobMap2.put("jobTicket", "Part-1mcmpl412wjvovt6ur");
		updateJobMap2.put("endTime", "03/27/2018 18:27:17");
		updateJobMap2.put("datasetSize", "3500");
		updateJobMap2.put("processedCount", "2495");
		updateJobMap2.put("validationPassed", "99");
		updateJobMap2.put("status", "In Progress");
		
		HashMap<String, String> updateJobMap3 = new HashMap<>();
		updateJobMap3.put("jobTicket", "Document-1mcmpl412wjvovt6x9");
		updateJobMap3.put("endTime", "03/27/2018 18:27:17");
		updateJobMap3.put("datasetSize", "3500");
		updateJobMap3.put("processedCount", "2495");
		updateJobMap3.put("validationPassed", "99");
		updateJobMap3.put("status", "In Progress");
		
		jobUpdateList.add(updateJobMap1);
		jobUpdateList.add(updateJobMap2);
		jobUpdateList.add(updateJobMap3);
		boolean outcome = this.cockPitRestClient.updateBulkJob(bulkUpdateJobsUrl, jobUpdateList);
		Assert.assertTrue(outcome);
	}
	
	@Test
	public void testUpdateBulkFileStatus() throws Exception {
		ArrayList<HashMap<String,String>> updateFileList = new ArrayList<>();
		
		HashMap<String, String> updateFileHashMap1 = new HashMap<>();
		updateFileHashMap1.put("name", "file1.txt");
		updateFileHashMap1.put("enlisted", "Completed");
		updateFileHashMap1.put("extracted", "Not Completed");
		updateFileHashMap1.put("inCleaning", "Not Completed");
		updateFileHashMap1.put("cleaned", "Not Completed");
		updateFileHashMap1.put("verified", "Completed");
		updateFileHashMap1.put("mapped", "Completed");
		updateFileHashMap1.put("inLoadtest", "Completed");
		updateFileHashMap1.put("transferred", "Not Completed");
		updateFileHashMap1.put("validated", "Completed");
		updateFileHashMap1.put("inProd", "Not Completed");
		
		HashMap<String, String> updateFileHashMap2 = new HashMap<>();
		updateFileHashMap2.put("name", "file1.txt");
		updateFileHashMap2.put("enlisted", "Completed");
		updateFileHashMap2.put("extracted", "Not Completed");
		updateFileHashMap2.put("inCleaning", "Not Completed");
		updateFileHashMap2.put("cleaned", "Not Completed");
		updateFileHashMap2.put("verified", "Completed");
		updateFileHashMap2.put("mapped", "Completed");
		updateFileHashMap2.put("inLoadtest", "Completed");
		updateFileHashMap2.put("transferred", "Not Completed");
		updateFileHashMap2.put("validated", "Completed");
		updateFileHashMap2.put("inProd", "Not Completed");
		
		HashMap<String, String> updateFileHashMap3 = new HashMap<>();
		updateFileHashMap3.put("name", "file1.txt");
		updateFileHashMap3.put("enlisted", "Completed");
		updateFileHashMap3.put("extracted", "Not Completed");
		updateFileHashMap3.put("inCleaning", "Not Completed");
		updateFileHashMap3.put("cleaned", "Not Completed");
		updateFileHashMap3.put("verified", "Completed");
		updateFileHashMap3.put("mapped", "Completed");
		updateFileHashMap3.put("inLoadtest", "Completed");
		updateFileHashMap3.put("transferred", "Not Completed");
		updateFileHashMap3.put("validated", "Completed");
		updateFileHashMap3.put("inProd", "Not Completed");
		
		updateFileList.add(updateFileHashMap1);
		updateFileList.add(updateFileHashMap2);
		updateFileList.add(updateFileHashMap3);
		boolean outcome = this.cockPitRestClient.UpdateBulkFileStatus(bulkUpdateFileStatusUrl, updateFileList);
		Assert.assertTrue(outcome);
	}
	
	@Test
	public void testUpdateBulkObjectStatus() throws Exception {
		ArrayList<HashMap<String,String>> updateObjectList = new ArrayList<>();
		
		HashMap<String, String> updateObjectHashMap1 = new HashMap<>();
		updateObjectHashMap1.put("name", "object1");
		updateObjectHashMap1.put("enlisted", "Completed");
		updateObjectHashMap1.put("extracted", "Not Completed");
		updateObjectHashMap1.put("inCleaning", "Not Completed");
		updateObjectHashMap1.put("cleaned", "Not Completed");
		updateObjectHashMap1.put("verified", "Completed");
		updateObjectHashMap1.put("mapped", "Completed");
		updateObjectHashMap1.put("inLoadtest", "Completed");
		updateObjectHashMap1.put("transferred", "Not Completed");
		updateObjectHashMap1.put("validated", "Completed");
		updateObjectHashMap1.put("inProd", "Not Completed");
		
		HashMap<String, String> updateObjectHashMap2 = new HashMap<>();
		updateObjectHashMap2.put("name", "object2");
		updateObjectHashMap2.put("enlisted", "Completed");
		updateObjectHashMap2.put("extracted", "Not Completed");
		updateObjectHashMap2.put("inCleaning", "Not Completed");
		updateObjectHashMap2.put("cleaned", "Not Completed");
		updateObjectHashMap2.put("verified", "Completed");
		updateObjectHashMap2.put("mapped", "Completed");
		updateObjectHashMap2.put("inLoadtest", "Completed");
		updateObjectHashMap2.put("transferred", "Not Completed");
		updateObjectHashMap2.put("validated", "Completed");
		updateObjectHashMap2.put("inProd", "Not Completed");
		
		HashMap<String, String> updateObjectHashMap3 = new HashMap<>();
		updateObjectHashMap3.put("name", "object3");
		updateObjectHashMap3.put("enlisted", "Completed");
		updateObjectHashMap3.put("extracted", "Not Completed");
		updateObjectHashMap3.put("inCleaning", "Not Completed");
		updateObjectHashMap3.put("cleaned", "Not Completed");
		updateObjectHashMap3.put("verified", "Completed");
		updateObjectHashMap3.put("mapped", "Completed");
		updateObjectHashMap3.put("inLoadtest", "Completed");
		updateObjectHashMap3.put("transferred", "Not Completed");
		updateObjectHashMap3.put("validated", "Completed");
		updateObjectHashMap3.put("inProd", "Not Completed");
		
		updateObjectList.add(updateObjectHashMap1);
		updateObjectList.add(updateObjectHashMap2);
		updateObjectList.add(updateObjectHashMap3);
		boolean outcome = this.cockPitRestClient.UpdateBulkObjectStatus(bulkUpdateObjectStatusUrl, updateObjectList);
		Assert.assertTrue(outcome);
	}

}
