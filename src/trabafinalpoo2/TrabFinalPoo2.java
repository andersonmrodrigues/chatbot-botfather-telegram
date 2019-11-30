/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabafinalpoo2;

import bot.BotUtils;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import view.FrmPedido;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ANDER
 */
public class TrabFinalPoo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new BotUtils());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        FrmPedido pedido = new FrmPedido();

        pedido.setVisible(true);
    }

}
