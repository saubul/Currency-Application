package ru.saubulprojects.currencyapp.cbrrate.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import lombok.extern.slf4j.Slf4j;
import ru.saubulprojects.currencyapp.cbrrate.exception.CurrencyRateParsingException;
import ru.saubulprojects.currencyapp.cbrrate.model.CurrencyRate;

@Service
@Slf4j
public class CurrencyRateParserXml implements CurrencyRateParser{

	@Override
	public List<CurrencyRate> parse(String rateAsString) {
		ArrayList<CurrencyRate> rates = new ArrayList<CurrencyRate>();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			try(StringReader reader = new StringReader(rateAsString)) {
				Document doc = db.parse(new InputSource(reader));
				doc.getDocumentElement().normalize();
				NodeList nodeList = doc.getElementsByTagName("Valute");
				
				for(int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if(node.getNodeType() == Node.ELEMENT_NODE) {
						Element el = (Element) node;
						CurrencyRate rate = CurrencyRate.builder()
															.numCode(el.getElementsByTagName("NumCode").item(0).getTextContent())
															.charCode(el.getElementsByTagName("CharCode").item(0).getTextContent())
															.nominal(el.getElementsByTagName("Nominal").item(0).getTextContent())
															.name(el.getElementsByTagName("Name").item(0).getTextContent())
															.value(el.getElementsByTagName("Value").item(0).getTextContent())
														.build();
						rates.add(rate);
					}
				}
				
			}
			
		}catch(Exception ex) {
			log.error("XML parsing error, XML: {}", rateAsString, ex);
			throw new CurrencyRateParsingException(ex);
		}
		
		return rates;
	}

}
