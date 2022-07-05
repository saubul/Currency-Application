package ru.saubulprojects.currencyapp.currencytelbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import lombok.SneakyThrows;
import ru.saubulprojects.currencyapp.currencytelbot.bot.CurrencyBot;

@SpringBootApplication
@EnableEurekaClient
public class CurrencyTelBotApplication {
	
	@SneakyThrows
	public CurrencyTelBotApplication(CurrencyBot currencyBot) {
		TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
		api.registerBot(currencyBot);
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CurrencyTelBotApplication.class, args);
	}
	
}
