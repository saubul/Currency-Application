package ru.saubulprojects.currencyapp.currencytelbot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import ru.saubulprojects.currencyapp.currencytelbot.entity.Currency;

@Configuration
public class ApplicationConfig {
	
	@Bean(name = "sources")
	public List<InlineKeyboardButton> sources() {
		List<InlineKeyboardButton> sources = new ArrayList<>();
		sources.add(InlineKeyboardButton.builder().text("жа").callbackData("SOURCE:CBR").build());
		sources.add(InlineKeyboardButton.builder().text("ллба").callbackData("SOURCE:MOEX").build());
		return sources;
	}
	
	@Bean(name = "currencies")
	public List<InlineKeyboardButton> currencies() {
		List<InlineKeyboardButton> currencies = new ArrayList<>();
		for(int i = 0; i < Currency.values().length; i++) {
			currencies.add(InlineKeyboardButton.builder().text(Currency.values()[i].name()).callbackData("CURRENCY:" + Currency.values()[i].name()).build());
		}
		return currencies;
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
