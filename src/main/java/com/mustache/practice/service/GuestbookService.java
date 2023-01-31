package com.mustache.practice.service;

import com.mustache.practice.dto.GuestbookDTO;
import com.mustache.practice.dto.PageRequestDTO;
import com.mustache.practice.dto.PageResultDTO;
import com.mustache.practice.entity.Guestbook;

import java.time.format.DateTimeFormatter;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long id);

    void remove(Long id);

    void modify(GuestbookDTO dto);

    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity =
                Guestbook.builder()
                         .id(dto.getId())
                         .title(dto.getTitle())
                         .content(dto.getContent())
                         .writer(dto.getWriter())
                         .build();
        return entity;

    }

    default GuestbookDTO entityToDTO(Guestbook entity) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        GuestbookDTO dto =
                GuestbookDTO.builder()
                            .id(entity.getId())
                            .title(entity.getTitle())
                            .content(entity.getContent())
                            .writer(entity.getWriter())
                            .regDate(entity.getRegDate())
                            .modDate(entity.getModDate())
                            .formattedRegDate(entity.getRegDate()
                                                    .format(formatter))
                            .formattedModDate(entity.getModDate()
                                                    .format(formatter))
                            .build();
        return dto;

    }
}
