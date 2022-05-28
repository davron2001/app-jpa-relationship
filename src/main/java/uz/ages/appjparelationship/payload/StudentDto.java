package uz.ages.appjparelationship.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String firtName;
    private String lastName;
    private String city;
    private String district;
    private String street;
    private Integer groupId;
    private Integer subjectId;
}
