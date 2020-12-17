package letsCode.catalizator.controller;


import letsCode.catalizator.domain.Message;
import letsCode.catalizator.sevice.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/controller")
public class MainController {

    private final MessageService messageService;

    @Autowired
    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Flux<Message> list(@RequestParam(defaultValue = "0") Long start,
                              @RequestParam(defaultValue = "3") Long count) {
        return messageService.list();
              /*  Flux.just("Hello, reactive!",
                        "More then one",
                        "Third post",
                        "Fourth post",
                        "Fifth post")
                        .skip(start)
                        .take(count)
                        .map(Message::new);
               */
    }
    @PostMapping
    public Mono<Message> add(@RequestBody Message message){
        return messageService.addOne(message);
    }

}

