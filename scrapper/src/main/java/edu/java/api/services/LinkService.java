package edu.java.api.services;

import edu.java.api.dto.LinkUpdate;
import java.util.List;

public interface LinkService {

    void handleUpdate();

    List<LinkUpdate> getOldUrl();

    boolean updateUrl(Long id, String time);
}
