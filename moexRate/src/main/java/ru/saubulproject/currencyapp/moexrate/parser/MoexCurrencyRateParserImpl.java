package ru.saubulproject.currencyapp.moexrate.parser;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.extern.slf4j.Slf4j;
import ru.saubulproject.currencyapp.moexrate.model.CurrencyRate;
import ru.saubulproject.currencyapp.moexrate.exception.CurrencyRateParsingException;

@Service
@Slf4j
public class MoexCurrencyRateParserImpl implements MoexCurrencyRateParser {@Override
	
	public CurrencyRate parse(String rateAsXml) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new InputSource(new StringReader(rateAsXml)));
			
			doc.normalize();
			
			NodeList nodeList = doc.getElementsByTagName("rate");
			CurrencyRate currencyRate = null;
			for(int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				System.out.println(node + "-------------------------------");
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) node;
					currencyRate = CurrencyRate.builder()
												   .value(el.getAttribute("value"))
											   .build();
					break;
				}
			}
			
			return currencyRate;
		} catch (Exception e) {
			log.error("Parser error: {}", e.toString());
			throw new CurrencyRateParsingException(e);
		}
	}
	
}
