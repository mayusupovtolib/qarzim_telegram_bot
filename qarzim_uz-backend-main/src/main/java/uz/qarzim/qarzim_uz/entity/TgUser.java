package uz.qarzim.qarzim_uz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.qarzim.qarzim_uz.entity.base.AbsEntityLong;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TgUser extends AbsEntityLong {

    private String fullName;

    private String chatId;

    private String username;

    private String botState;

    private String botType;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public TgUser(String fullName, String chatId, String username) {
        this.fullName = fullName;
        this.chatId = chatId;
        this.username = username;
    }
}
