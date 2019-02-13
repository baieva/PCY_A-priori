import java.io.*;
//Mingjia Shi
public class Tester {
	
	static long start,end,dif;
	static double[] thresholdp = {0.01, 0.05, 0.1};
	static double[] basketsize = {0.01, 0.05, 0.10, 0.20, 0.3, 0.4, 0.5, 0.6, 0.7 ,0.8 , 0.9, 1};

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		for(int t = 0; t < thresholdp.length; t++) {
			for(int b = 0; b < basketsize.length; b++) {
				String line = null;
				try {
					String filepath = "retail.txt";//file name
					FileReader file = new FileReader(filepath);
					BufferedReader br = new BufferedReader(file);
					int linesof = 0;
					while((line = br.readLine()) != null) {//count baskets
						linesof++;
					}
					int maxbacket = (int) (linesof * basketsize[b]);//baskets for each data percent
					int threshold = (int) (maxbacket * thresholdp[t]);//thresholds
					start = System.currentTimeMillis();
					//apri
					APCount ap = new APCount(threshold,maxbacket,filepath);
					end = System.currentTimeMillis();
					dif = end - start;
					System.out.println(basketsize[b] + "% A-priori " + thresholdp[t] + "% " + dif + "ms");
				
					start = System.currentTimeMillis();
					//PCY
					PCYCount pcy = new PCYCount(threshold,maxbacket, filepath);
					end = System.currentTimeMillis();
					dif = end - start;
					System.out.println(basketsize[b] + "% PCY " + thresholdp[t] + "% " + dif + "ms");
					br.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
