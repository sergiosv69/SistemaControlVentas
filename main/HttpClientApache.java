/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author Sergio Vasquez
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientApache
{
  String USER_AGENT = "Mozilla/5.0";
  
  public BufferedReader sendGet(String URL)
    throws Exception
  {
    String url = URL;
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    request.addHeader("User-Agent", this.USER_AGENT);
    HttpResponse response = client.execute(request);
    System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    StringBuilder result = new StringBuilder();
    
    return rd;
  }
  
  public BufferedReader sendPost(String URL, String[][] parametro)
    throws Exception
  {
    String url = URL;
    HttpClient client = new DefaultHttpClient();
    HttpPost post = new HttpPost(url);
    post.setHeader("User-Agent", this.USER_AGENT);
    List<NameValuePair> urlParameters = new ArrayList();
    for (int x = 0; x < parametro.length; x++)
    {
      System.out.println(parametro[x][0] + " " + parametro[x][1]);
      urlParameters.add(new BasicNameValuePair(parametro[x][0], parametro[x][1]));
    }
    post.setEntity(new UrlEncodedFormEntity(urlParameters));
    HttpResponse response = client.execute(post);
    System.out.println("Post parameters : " + post.getEntity());
    System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    
    return rd;
  }
}
