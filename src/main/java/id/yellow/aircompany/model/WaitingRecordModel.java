package id.yellow.aircompany.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingRecordModel {

    private long id;
    private long userId;
    private String destinationTo;
}
