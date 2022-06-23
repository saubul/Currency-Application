package ru.saubulproject.currencyapp.moexrate.requester;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.saubulproject.currencyapp.moexrate.exception.MoexRequesterException;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoexRestRequesterImpl  implements MoexRequester{

	private final RestTemplate restTemplate;
	
	@Override
	public String getRateAsXml(String url) {
		try {
			log.info("getRatesAsXml, url: {}", url);
			return restTemplate.getForObject(url, String.class);
		} catch (Exception ex) {
			log.error("MoexRestRequesterImpl error: {}", ex.toString());
			throw new MoexRequesterException(ex);
		}
	}
	
}
