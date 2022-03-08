package kr.co.neighbor21.homepage.control;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/html2canvas")
public class Html2CanvasProxy {
	
	@RequestMapping(value="/proxy.json", method=RequestMethod.GET)
	@ResponseBody
	public byte[] html2canvasProxy(HttpServletRequest req) {
		byte[] data = null;
		try {
			URL url = new URL(URLDecoder.decode(req.getParameter("url"), java.nio.charset.StandardCharsets.UTF_8.toString()));
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			if(connection.getResponseCode() == 200) {
				data = IOUtils.toByteArray(connection.getInputStream());
			} else {
				System.out.println("responseCode : " + connection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			data = "wrong URL".getBytes(java.nio.charset.StandardCharsets.UTF_8);
		} catch(Exception e) {
			System.out.println(e);
		}
		return data;
	}
}
