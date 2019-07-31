import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot{
    private static final String botUserName = "tesafbfndjksjnkfvdsbot";
    private static final String token = "712444289:AAGEHKqRVapP8TtUTCaCHTpuuALswIogp64";

    public static void main(String[] args) {
        System.out.println("start");
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        if(update.hasMessage()){
            if(update.getMessage().hasText()){
                if(update.getMessage().getText().equals("/start")){
                    try {
                        execute(newRequestMessage(update));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else if(update.hasCallbackQuery()){
            try {
                execute(new SendMessage().setText(
                        update.getCallbackQuery().getData())
                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
    public static SendMessage newRequestMessage(Update update) {
        long chatId = update.getMessage().getChatId();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton accept = new InlineKeyboardButton();
        InlineKeyboardButton decline = new InlineKeyboardButton();
        accept.setText("Accept");
        accept.setCallbackData("Accepted by " + update.getMessage().getFrom().getUserName() + "\n" +
                "Thank you for your attention");
        decline.setText("Decline");
        decline.setCallbackData("Declined by " + update.getMessage().getFrom().getUserName() + "\n" +
                "Thank you for your attention");
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(accept);
        keyboardButtonsRow.add(decline);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId)
                .setText("New request from colibrisoft website:\n" +
                        "Who can contact with client? ")
                .setReplyMarkup(inlineKeyboardMarkup);
    }
}