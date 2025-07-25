package ir.maktab.cw29.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(value = Log.DOCUMENT_NAME)
@Setter
@Getter
@NoArgsConstructor
public class Log {
    public static final String DOCUMENT_NAME = "logs";
    public static final String USER_ID = "user_id";
    public static final String ACTION_ID = "action_id";
    public static final String ACTION = "action";
    public static final String STATUS = "status";
    public static final String DATETIME = "datetime";

    @Field(value = USER_ID)
    private Long userId;

    @Field(value = ACTION_ID)
    private String actionId;

    @Field(value = ACTION)
    private String action;

    @Field(value = STATUS)
    private ActionStatus status;

    @Field(value = DATETIME)
    private LocalDateTime datetime;

}
