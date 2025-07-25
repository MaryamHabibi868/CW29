package ir.maktab.cw29.dto;

import ir.maktab.cw29.document.ActionStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserRequestPerStatusDto {
    public ActionStatus id;
    public Integer numberOfRequest;
}
