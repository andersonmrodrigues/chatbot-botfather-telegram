/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 *
 * @author anderson
 */
public class BotUtils extends TelegramLongPollingBot {

    private static String token = "1029226417:AAGGRxjQUin91S4yJ13HJ7EByrXajwQ4O0U";
    private static String link = "t.me/GeraldoPoo2Bot";
    private static String botUser = "GeraldoPoo2Bot";
    private static final String endpoint = "https://api.telegram.org/";

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String mensagem = update.getMessage().getText();
            long id = update.getMessage().getChatId();
            enviaMensagem(id, mensagem);

        }
    }

    @Override
    public String getBotUsername() {
        return botUser;
    }

    private void enviaMensagem(long id, String mensagem) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(id)
                .setText(mensagem);
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
