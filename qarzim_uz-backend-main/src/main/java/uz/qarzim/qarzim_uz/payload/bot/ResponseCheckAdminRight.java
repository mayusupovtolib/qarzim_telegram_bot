package uz.qarzim.qarzim_uz.payload.bot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMemberAdministrator;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCheckAdminRight {

    private boolean ok;

    private List<ChatMemberAdministrator> result;
}
