package sla.util;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import de.l3s.boilerpipe.extractors.ArticleExtractor;

public class GetPageUtil {

	public static String get(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/534.52.7 (KHTML, like Gecko) Version/5.1.2 Safari/534.52.7");
		connection
				.setRequestProperty("Accept",
						"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		connection.setRequestProperty("Cache-Control", "max-age=0");
		connection.setRequestMethod("GET");

		BufferedReader br = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));
		String content = "";
		String line;
		while ((line = br.readLine()) != null) {
			content += line + "\n";
		}
		return content;
	}
	
	public static List<String> getImageUrls(String urlStr) throws Exception {
		String content = get(urlStr);
		
		List<String> imageUrls = new ArrayList<String>();
		
		Pattern p = Pattern.compile("<img src=\"(.*?)\"");
		Matcher m = p.matcher(content);

		while (m.find()) {
			String imageUrl = m.group(1);
			//System.out.println(imageUrl);
			imageUrls.add(imageUrl);
		}
		return imageUrls;
	}
	public static String getPageTitle(URL url) throws Exception {


	    BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream(),"UTF-8"));
	    
	    Pattern pHead = Pattern.compile("(?i)</HEAD>");
	    Matcher mHead;
	    Pattern pTitle = Pattern.compile("(?i)</TITLE>");
	    Matcher mTitle;
	    
	    String inputLine;
	    boolean found=false;
	    boolean notFound=false;
	    String html = "";
	    String title=new String();
	    try{
		    while (!(((inputLine = in.readLine()) == null) || found || notFound)){
		    	html=html+inputLine;
		    	mHead=pHead.matcher(inputLine);
		    	if(mHead.find()){
		    		notFound=true;
		    		}
		    	else{
		    		mTitle=pTitle.matcher(inputLine);
		    		if(mTitle.find()){
		    			found=true;
		    		}
		    	}	    					    		
		    }
		    in.close();
	   
		    html = html.replaceAll("\\s+", " ");
		    if(found){
			    Pattern p = Pattern.compile("(?i)<TITLE.*?>(.*?)</TITLE>");
			    Matcher m = p.matcher(html);		    
			    while (m.find() == true) {
			    	title=m.group(1);
			    }
		    }
	    }catch(Exception e){
	    }
	    return title;
	  }
	public static void main(String[] args) throws Exception {
		String mainUrl="http://sports.news.naver.com/sports/index.nhn?category=baseball&ctg=news&mod=read&office_id=117&article_id=0002283863";
		List<String > images=getImageUrls(mainUrl);
		int max=0;
		String maxUrl="";
		for(int i=0;i<images.size();i++){
			String urlStr=images.get(i);
			if(urlStr.startsWith("/")){
				urlStr=mainUrl+urlStr;
			}
			URL url = new URL(urlStr);
			
	        Image image = ImageIO.read(url);
	        int width = image.getWidth(null);
	        int height = image.getHeight(null);
	        if(width*height>max){
	        	max=width*height;
	        	maxUrl=urlStr;
	        }
		}
		System.out.println("max:"+maxUrl);
		System.out.println(getPageTitle(new URL("http://osen.mt.co.kr/article/G1109496920")));
		
		
	}

}
