package edu.java.api.services.jpa;

import edu.java.api.model.entity.TgChatEntity;
import edu.java.api.model.repository.jpa.JpaTgChatRepository;
import edu.java.api.services.interfaces.TgChatService;

public class JpaTgChatServiceImpl implements TgChatService {

    private final JpaTgChatRepository jpaTgChatRepository;

    public JpaTgChatServiceImpl(JpaTgChatRepository jpaTgChatRepository) {
        this.jpaTgChatRepository = jpaTgChatRepository;
    }

    @Override
    public void regNewTgChat(Long id) {
        jpaTgChatRepository.saveAndFlush(new TgChatEntity(id));
    }

    @Override
    public void deleteTgChat(Long id) {
        jpaTgChatRepository.remove(id);
    }
}
