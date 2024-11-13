package uz.qarzim.qarzim_uz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.qarzim.qarzim_uz.service.BotService;

@RestController
@RequestMapping("/bot")
@RequiredArgsConstructor
public class BotControllor {

    private final BotService botService;

    @PostMapping
    public void getUpdateFromUserBot(@RequestBody Update update) {
        botService.getUpdateFromUser(update);
    }
}
