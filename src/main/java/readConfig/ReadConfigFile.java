package readConfig;

import java.io.IOException;
import java.util.Properties;

import commonLibrary.CommonLibrary;

public class ReadConfigFile {
	static Properties prop;
	public static String getReportConfigPath() throws IOException {
		
		prop=CommonLibrary.raedProprtyFiles();
		String reportConfigPath = prop.getProperty("reportConfigPath");
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}
	
public static String getSnapShotPath() throws IOException {
		
		prop=CommonLibrary.raedProprtyFiles();
		String snapShotPath = prop.getProperty("fileWithPath");
		if (snapShotPath != null)
			return snapShotPath;
		else
			throw new RuntimeException(
					"SnapShot Path not specified in the Configuration.properties file for the fileWithPath");
	}

}
