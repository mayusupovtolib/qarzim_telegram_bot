package uz.qarzim.qarzim_uz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.qarzim.qarzim_uz.entity.TgUser;
import uz.qarzim.qarzim_uz.entity.enums.BotState;
import uz.qarzim.qarzim_uz.payload.bot.ResponseCheckAdminRight;
import uz.qarzim.qarzim_uz.payload.bot.ResponseFromCheckChannel;
import uz.qarzim.qarzim_uz.repository.TgUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BotService {

    private final TgUserRepository tgUserRepository;

    private final RestTemplate restTemplate;

    @Value("${bot.token.for.user}")
    private String botTokenForUser;

    public void getUpdateFromUser(Update update) {
        try {
            TgUser tgUser = getUserByChatId(update);
            if (tgUser.getChatId() != null) {
                if (update.hasMessage()) {
                    String text = update.getMessage().getText();
                    if (text != null && text.equals("/start")) {
                        allIsOk(update, tgUser);
                    }
                } else {
                    String text = update.getCallbackQuery().getData();
                    System.out.println("CallBackQuery =>" + text);
                }

            } else {
                SendMessage message = new SendMessage(getChatId(update), "Foydalanishni  \uD83D\uDC49 '/start' dan boshlang!");
                sendMessage(message, botTokenForUser);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void allIsOk(Update update, TgUser tgUser) {
        SendMessage message = new SendMessage();
        message.setChatId(tgUser.getChatId());
        message.setText("Assalomu alaykum \n" +
                "\n" +
                "Botimizga xush kelibsiz\uD83D\uDD25\n" +
                "\n" +
                "Men siz uchun pullaringizni to'g'ri saqlashda va hisobda adashmasligingizda yordam beraman\uD83D\uDE09");
        sendMessage(message, botTokenForUser);
    }


    //    ******************************* GLOBAL METHODS *******************************
    private boolean checkAdminRightForChannel(String username, String token) {
        try {
            ResponseCheckAdminRight response = restTemplate.getForObject("https://api.telegram.org/bot" + token + "/getChatAdministrators?chat_id=@" + username, ResponseCheckAdminRight.class);
            return response != null && response.isOk();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private boolean checkChannel(String username, TgUser tgUser, String token) {
        ResponseFromCheckChannel response = restTemplate.getForObject("https://api.telegram.org/bot" + token + "/getChatMember?chat_id=@" + username + "&user_id=" + tgUser.getChatId(), ResponseFromCheckChannel.class);
        return response != null
                && response.getOk() != null
                && response.getOk()
                && !response.getResult().getStatus().equals("left");
    }

    private void changeBotState(TgUser tgUser, BotState state) {
        tgUser.setBotState(state.name());
        tgUserRepository.save(tgUser);
    }

    private void sendMessage(SendMessage message, String token) {
        try {
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendMessage", message, Object.class);
        } catch (Exception e) {
            message.setText("Kechirasiz kutilmagan xatolik chiqdi. Buning uchun juda afsusdamiz. \uD83D\uDE2D\uD83D\uDE2D");
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendMessage", message, Object.class);
        }
    }

    private void updateMessageReplyMarkup(EditMessageReplyMarkup message, String token) {
        try {
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/editMessageReplyMarkup", message, Object.class);
        } catch (Exception e) {
//            message.setText("Kechirasiz kutilmagan xatolik chiqdi. Buning uchun juda afsusdamiz. \uD83D\uDE2D\uD83D\uDE2D");
//            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendMessage", message, Object.class);
        }
    }

    private void updateMessage(EditMessageText message, String token) {
        try {
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/editMessageText", message, Object.class);
        } catch (Exception e) {
            message.setText("Kechirasiz kutilmagan xatolik chiqdi. Buning uchun juda afsusdamiz. \uD83D\uDE2D\uD83D\uDE2D");
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/sendMessage", message, Object.class);
        }
    }

    private void answerCallbackQuery(String callBackQueryId, String text, String chatId, String token) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callBackQueryId);
        answerCallbackQuery.setText(text);
        try {
            System.out.println("callBackQueryId=" + callBackQueryId);
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/answerCallbackQuery", answerCallbackQuery, Object.class);
        } catch (Exception e) {
            SendMessage message = new SendMessage(chatId, "Kechirasiz kutilmagan xatolik chiqdi. Buning uchun juda afsusdamiz. \uD83D\uDE2D\uD83D\uDE2D");
            sendMessage(message, token);
        }
    }

    private void answerCallbackQuery(String callBackQueryId, String text, Boolean enableAlert, String chatId, String token) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callBackQueryId);
        answerCallbackQuery.setText(text);
        answerCallbackQuery.setShowAlert(enableAlert);
        try {
            restTemplate.postForObject("https://api.telegram.org/bot" + token + "/answerCallbackQuery", answerCallbackQuery, Object.class);
        } catch (Exception e) {
            SendMessage message = new SendMessage(chatId, "Kechirasiz kutilmagan xatolik chiqdi. Buning uchun juda afsusdamiz. \uD83D\uDE2D\uD83D\uDE2D");
            sendMessage(message, token);
        }
    }

    private void sendVideo(String chatId, String video, String caption, String parseMode, Boolean hasSpoiler, ReplyKeyboard replyMarkup, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("chat_id", chatId);
        map.add("video", video);
        map.add("caption", caption);
        map.add("parse_mode", parseMode);
        map.add("has_spoiler", hasSpoiler);
        map.add("reply_markup", replyMarkup);

        HttpEntity<MultiValueMap> request = new HttpEntity<>(map, headers);

        try {
            restTemplate.postForEntity("https://api.telegram.org/bot" + token + "/sendVideo", request, String.class);
        } catch (Exception e) {
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("Videoni yuborishda kutilmagan xatolik. Iltimos qaytadan urunib ko'ring.");
            sendMessage(message, token);
        }
    }

    private void deleteMessage(DeleteMessage deleteMessage, String token) {
        restTemplate.postForObject("https://api.telegram.org/bot" + token + "/deleteMessage", deleteMessage, Object.class);
    }

    private TgUser getUserByChatId(Update update) {
        Optional<TgUser> optionalUser = tgUserRepository.findByChatIdAndDeletedFalse(getChatId(update));
        return optionalUser.orElseGet(() -> new TgUser(update.getMessage().getChat().getFirstName() + " "
                + update.getMessage().getChat().getLastName(), getChatId(update), update.getMessage().getChat().getUserName()));
    }

    private String getChatId(Update update) {
        if (update.hasMessage()) {
            System.out.println(update.getMessage().getChat().getFirstName() + " => " + update.getMessage().getChatId().toString());
            return update.getMessage().getChatId().toString();
        }
        return update.getCallbackQuery().getMessage().getChatId().toString();
    }
}
