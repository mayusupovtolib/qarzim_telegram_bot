package uz.qarzim.qarzim_uz.payload.bot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySendVideo {

    @JsonProperty("chat_id")
    private String chat_id;

    @JsonProperty("video")
    private String video;

    @JsonProperty("caption")
    private String caption;

    @JsonProperty("parse_mode")
    private String parse_mode;

    @JsonProperty("has_spoiler")
    private Boolean has_spoiler;

    @JsonProperty("reply_markup")
    private ReplyKeyboard reply_markup;

    public MySendVideo(String chatId, String video) {
        this.chat_id = chatId;
        this.video = video;
    }

    public void enableMarkdown(boolean enable) {
        if (enable) {
            this.parse_mode = "Markdown";
        } else {
            this.parse_mode = null;
        }

    }

    public void enableHtml(boolean enable) {
        if (enable) {
            this.parse_mode = "html";
        } else {
            this.parse_mode = null;
        }

    }

    public void enableMarkdownV2(boolean enable) {
        if (enable) {
            this.parse_mode = "MarkdownV2";
        } else {
            this.parse_mode = null;
        }

    }
}
