package ru.saubulprojects.currencyapp.currencytelbot.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import ru.saubulprojects.currencyapp.currencytelbot.entity.Currency;
import ru.saubulprojects.currencyapp.currencytelbot.service.CurrencyService;

@Component
@RequiredArgsConstructor
public class CurrencyBot extends TelegramLongPollingBot{
	
	@Value("${bot.username}")
	private String username;
	
	private String token = System.getenv("token");
	
	private String source = "CBR";
	private String currency = "USD";
	private String date = "2020-01-01";

	@Qualifier(value = "sources")
	@Autowired
	private List<InlineKeyboardButton> sources;
	
	@Qualifier(value = "currencies")
	@Autowired
	private List<InlineKeyboardButton> currencies;
	
	private final CurrencyService currencyService;
	
	@Override
	@SneakyThrows
	public void onUpdateReceived(Update update) {
		if(update.hasMessage()) {
			handleMessage(update.getMessage());
		} else if(update.hasCallbackQuery()) {
			handleCallback(update.getCallbackQuery());
		}
	}

	@SneakyThrows
	private void handleCallback(CallbackQuery callbackQuery) {
		Message message = callbackQuery.getMessage();
		String[] param = callbackQuery.getData().split(":");
		String action = param[0];
		switch(action) {
			case "SOURCE":
				this.source = param[1];
				execute(SendMessage.builder()
								       .chatId(message.getChatId())
								       .text("Choose currency: ")
								       .replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(currencies).build())
								   .build());
				break;
			case "CURRENCY":
				this.currency = param[1];
				execute(SendMessage.builder()
								       .chatId(message.getChatId())
								       .text("Please enter the date (format: yyyy-MM-dd): ")
								    .build());
				break;
			
		}
	}

	@SneakyThrows
	private void handleMessage(Message message) {
		if(message.hasText() && message.hasEntities()) {
			Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
			if(commandEntity.isPresent()) {
				String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
				switch(command) {
					case "/get_currency_rate":
						execute(SendMessage.builder()
										       .chatId(message.getChatId())
										       .text("Please choose where from you want to get information: ")
										       .replyMarkup(InlineKeyboardMarkup.builder().keyboardRow(sources).build())
										   .build());
						break;
				}
			}
		} else if (message.hasText()) {
			//TODO validate date
			this.date = message.getText();
			execute(SendMessage.builder()
								   .chatId(message.getChatId())
								   .text(String.format(currency + " rate on " + date + ": %s RUB", currencyService.getCurrency(source, currency, date)))
							   .build());
		}
	}

	@Override
	public String getBotUsername() {
		return this.username;
	}

	@Override
	public String getBotToken() {
		return this.token;
	}
	
}
