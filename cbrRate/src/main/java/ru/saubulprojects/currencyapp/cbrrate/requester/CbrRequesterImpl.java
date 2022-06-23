package ru.saubulprojects.currencyapp.cbrrate.requester;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.cbrrate.exception.RequesterException;

@Service
@Slf4j
public class CbrRequesterImpl implements CbrRequester{

	@Override
	public String getRatesAsXml(String url) {
		try {
			log.info("Request for url: {}", url);
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
											 	.uri(URI.create(url))
											 .build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
			
		} catch(Exception ex) {
			if(ex instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}
			log.error("CBR request error, url: {}", url, ex);
			throw new RequesterException(ex);
		}
	}

}
