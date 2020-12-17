package letsCode.catalizator.sevice;

import letsCode.catalizator.domain.Message;
import letsCode.catalizator.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageService {

    private final MessageRepo messageRepo;
    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Flux<Message> list (){
        return messageRepo.findAll();
    }
    public Mono<Message> addOne (Message message){
        return messageRepo.save(message);
    }
}
