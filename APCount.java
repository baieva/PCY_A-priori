import java.util.*;
import java.io.*;
//Mingjia Shi
public class APCount {

	static Hashtable<String, Integer> chunck = new Hashtable<String,Integer>(); //first integer is key second is value
	static Hashtable<String, Integer> items = new Hashtable<String,Integer>(); //first integer is key second is value
	static StringTokenizer token;
	private static int threshold;
	private static int maxbasket;
	
	public APCount(int threshold, int maxbasket, String file) throws Exception {
		this.threshold = threshold;
		this.maxbasket = maxbasket;
		Passone(file);
		Passtwo(file);
		//System.out.println(chunck.toString());
	}
	
	public static void Passone(String filepath) throws Exception{
		String line = null;
		FileReader file = new FileReader(filepath);
		BufferedReader br1 = new BufferedReader(file);
		int linecount = 0;
		while((line = br1.readLine()) != null && linecount <= maxbasket) {//read baskets
			token = new StringTokenizer(line);//to separate data
			String keys = null;
			while(token.hasMoreTokens()) {
				keys = token.nextToken();
				if(items.containsKey(keys)) {//put data into hastable
					int t = items.get(keys);
					items.put(keys, t + 1);
				}
				else {
					items.put(keys, 1);
				}
			}
			linecount ++;//for count max data set size
		}
	}
	
	public static void Passtwo(String filepath) throws Exception {
		String line = null;
		FileReader file = new FileReader(filepath);
		BufferedReader br2 = new BufferedReader(file);
		int linecount = 0;
		while((line = br2.readLine()) != null && linecount <= maxbasket) {//read baskets
			token = new StringTokenizer(line);//to separate data
			String key = null;
			String keyi = null;
			String[] keys = new String[token.countTokens()];
			int i = 0;
			while(token.hasMoreTokens()) {
				keys[i] = token.nextToken();
				i++;
			}
			for(int j = 0; j < i - 1; j++) {
				if(items.get(keys[j]) < threshold) continue;//check frequency
				//System.out.println(keys[j]);
				for(int k = j + 1; k < i; k++) {
					if(items.get(keys[k]) < threshold) continue;//check frequency
					//System.out.println(keys[k]);
					key = keys[j] + " " + keys[k];//put frequent pair into a hashtable easy to print out
					keyi = keys[k] + " " + keys[j];
					if(chunck.containsKey(key)) {
						int t = chunck.get(key);
						chunck.put(key, t + 1);
					}
					else if (chunck.containsKey(keyi)) {
						int t = chunck.get(keyi);
						chunck.put(keyi, t + 1);
					}
					else {
						chunck.put(key, 1);
					}
				}
			}
			linecount ++;//for count max data set size
		}
		br2.close();
	}
}
