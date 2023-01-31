package com.mustache.practice.service;

import com.mustache.practice.dto.GuestbookDTO;
import com.mustache.practice.dto.PageRequestDTO;
import com.mustache.practice.dto.PageResultDTO;
import com.mustache.practice.entity.Guestbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GuestbookServiceImplTest {

    @Autowired
    private GuestbookService service;

    @Test
    public void pageRequestDTOAndPageResultDTO() {
        PageRequestDTO pageRequestDTO =
                PageRequestDTO.builder()
                              .page(1)
                              .size(10)
                              .type("t")
                              .keyword("19")
                              .build();
        PageResultDTO<GuestbookDTO, Guestbook> list = service.getList(pageRequestDTO);

        System.out.println("PREV : " + list.isPrev());
        System.out.println("NEXT : " + list.isNext());
        System.out.println("TOTAL : " + list.getTotalPage());
        System.out.println("=========================================================");
        list.getDtoList()
            .forEach(i -> System.out.println(i));
        System.out.println("=========================================================");

        list.getPageList()
            .forEach(i -> System.out.println(i));

    }

}

//    @Test
//    public void testRegister() {
//        GuestbookDTO guestbookDTO =
//                GuestbookDTO.builder()
//                            .title("Sample title")
//                            .content("Sample content")
//                            .writer("user0")
//                            .build();
//        System.out.println(service.register(guestbookDTO));
//    }
//
//    @Test
//    public void testList() {
//        PageRequestDTO pageRequestDTO =
//                PageRequestDTO.builder()
//                              .page(1)
//                              .size(10)
//                              .build();
//        PageResultDTO<GuestbookDTO, Guestbook> result = service.getList(pageRequestDTO);
//        for (GuestbookDTO guestbookDTO : result.getDtoList()) {
//            System.out.println(guestbookDTO);
//        }
//    }
