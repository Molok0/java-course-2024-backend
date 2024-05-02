package edu.java.api.services;

import edu.java.api.dto.LinkUpdate;
import java.time.OffsetDateTime;
import java.util.List;

public interface LinkService {

    List<LinkUpdate> getOldUrl();

    boolean updateUrl(Long id, OffsetDateTime time);
}
