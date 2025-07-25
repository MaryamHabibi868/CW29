package ir.maktab.cw29.dto;

import ir.maktab.cw29.document.ActionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogDto {

    private Long userId;

    private String actionId;

    private String action;

    private ActionStatus status;

    private LocalDateTime datetime;
}
