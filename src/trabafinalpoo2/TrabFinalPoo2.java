/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabafinalpoo2;

import bot.BotTelegram;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import view.FrmPedido;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Anderson e Jean
 */
public class TrabFinalPoo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Iniciando a API do telegram
        ApiContextInitializer.init();

        // Instanciando a API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Registrando o bot na API com as credencias
        try {
            botsApi.registerBot(new BotTelegram());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        FrmPedido pedido = new FrmPedido();

        pedido.setVisible(true);
    }

}
