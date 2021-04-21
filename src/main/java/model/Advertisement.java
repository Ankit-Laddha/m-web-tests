package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;


@Setter
@NoArgsConstructor
@Accessors(fluent = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advertisement {
    public String name;
    public String street;
    public int rooms;
    public int price;
    public boolean status;
}
