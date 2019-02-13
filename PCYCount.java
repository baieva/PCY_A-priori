import java.util.*;
import java.io.*;
//Mingjia Shi
public class PCYCount {
	
	static Hashtable<String, Integer> items = new Hashtable<String,Integer>(); //first integer is key second is value
	static Hashtable<String, Integer> chunck = new Hashtable<String,Integer>(); //first integer is key second is value
	static int maxbucket;
	static int[] bucket = new int[(int) Math.pow(2, 20)];//size of bucket need to be sufficiently large
	static StringTokenizer token;
	private static int threshold;
	private static int maxbasket;
	
	public PCYCount(int threshold, int maxbasket, String file) throws Exception {
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
			String[] keys = new String[token.countTokens()];
			int i = 0;
			while(token.hasMoreTokens()) {
				keys[i] = token.nextToken();
				if(items.containsKey(keys[i])) {//put elements into hashtable
					int t = items.get(keys[i]);
					items.put(keys[i], t + 1);
				}
				else {
					items.put(keys[i], 1);
				}
				i++;
				maxbucket++;
			}
			for(int j = 0; j < i - 1; j++) {
				for(int k = j + 1; k < i; k++) {
					int hc = Integer.parseInt(keys[j]) + Integer.parseInt(keys[k]);
					bucket[Integer.valueOf(hc).hashCode()]++;//hashcode
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
				if(items.get(keys[j]) < threshold) continue;//check element frequency
				for(int k = j + 1; k < i; k++) {
					if(items.get(keys[k]) < threshold) continue;//check element frequency
					int hc = Integer.parseInt(keys[j]) + Integer.parseInt(keys[k]);//hashcode
					key = keys[j] + " " + keys[k];
					keyi = keys[k] + " " + keys[j];
					if(bucket[Integer.valueOf(hc).hashCode()] < threshold) continue;//check the bucket if frequent
					if(chunck.containsKey(key)) {//put frequent pair into a hashtable easy to print out
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
