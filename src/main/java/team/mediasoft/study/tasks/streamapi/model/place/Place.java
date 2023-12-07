package team.mediasoft.study.tasks.streamapi.model.place;

import lombok.Data;

@Data
public class Place {

    private String region;
    private PlaceType type;
    private String locality;
}
