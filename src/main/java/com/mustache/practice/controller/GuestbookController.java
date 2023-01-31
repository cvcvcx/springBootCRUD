package com.mustache.practice.controller;


import com.mustache.practice.dto.GuestbookDTO;
import com.mustache.practice.dto.PageRequestDTO;
import com.mustache.practice.dto.PageResultDTO;
import com.mustache.practice.entity.Guestbook;
import com.mustache.practice.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RequestMapping("/guestbook")
@RestController
public class GuestbookController {

    private final GuestbookService guestbookService;


    @GetMapping("/list")
    public PageResultDTO<GuestbookDTO, Guestbook> list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("list" + pageRequestDTO);
        PageResultDTO<GuestbookDTO, Guestbook> list = guestbookService.getList(pageRequestDTO);

        return list;
    }

    @GetMapping("/read")
    public GuestbookDTO read(long id) {
        log.info("id: {}", id);
        GuestbookDTO dto = guestbookService.read(id);
        return dto;
    }


    @PostMapping("/register")
    public String registerPost(@RequestBody GuestbookDTO dto) {
        log.info(dto);
        Long register = guestbookService.register(dto);
        return register.toString();
    }

    @PostMapping("/modify")
    public String modifyPost(@RequestBody GuestbookDTO dto) {
        log.info(dto);
        guestbookService.modify(dto);
        return "update";
    }

    @PostMapping("/delete")
    public String deletePost(long id) {
        log.info(id);
        guestbookService.remove(id);
        return "delete";
    }
}
