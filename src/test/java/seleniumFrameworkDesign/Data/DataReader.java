package seleniumFrameworkDesign.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader 
{

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		//1.to read the Json file and top covert it into String varibles
		//to remover deprecation error we have passed the format for the string characters to be converted which is StandardCharsets.UTF_8
		String jsondata = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\seleniumFrameworkDesign\\Data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//2.to convert string into hashmap
		ObjectMapper mapper = new ObjectMapper();
		//all the data in the jsonfile which is converted into string is now converted into a list containing hashmaps
		List<HashMap<String, String>> data = mapper.readValue(jsondata, new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}
}
