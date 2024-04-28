package edu.java.service.services.jpa;

import edu.java.domain.entity.TgChatEntity;
import edu.java.domain.repository.jpa.JpaTgChatRepository;
import edu.java.service.services.interfaces.TgChatService;

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
