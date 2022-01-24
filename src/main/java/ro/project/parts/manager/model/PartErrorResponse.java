package ro.project.parts.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
