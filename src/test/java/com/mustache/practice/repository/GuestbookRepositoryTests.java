package com.mustache.practice.repository;

import com.mustache.practice.entity.Guestbook;
import com.mustache.practice.entity.QGuestbook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;


    @Test
    public void testQuery1() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id")
                                                      .descending());

        String keyword = "1";

        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanExpression expression = qGuestbook.title.contains(keyword);

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(expression);

        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream()
              .forEach(i -> {
                  System.out.println(i);
              });

    }

    @Test
    public void testQuery2() {

        Pageable pageable = PageRequest.of(0, 10, Sort.by("id")
                                                      .descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        String keyword = "1";

        BooleanExpression expression = qGuestbook.title.contains(keyword);

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(expression);


        Page<Guestbook> result = guestbookRepository.findAll(builder, pageable);

        result.stream()
              .forEach(i -> {
                  System.out.println(i);
              });


    }

//
//    @Test
//    public void insertDummies(){
//
//        IntStream.rangeClosed(1,300).forEach(i->{
//            Guestbook guestbook = Guestbook.builder()
//                    .title("Title..."+i)
//                    .content("Content..."+i)
//                    .writer("Writer..."+(i%10))
//                    .build();
//            System.out.println(guestbookRepository.save(guestbook));
//        });
//
//    }
//    @Test
//    public void updateTest(){
//
//        Optional<Guestbook> guestbook = guestbookRepository.findById(300L);
//        if(guestbook.isPresent()){
//            Guestbook guestbook1 = guestbook.get();
//            guestbook1.changeTitle("Changed Title!");
//            guestbook1.changeContent("Changed Content!");
//
//            guestbookRepository.save(guestbook1);
//        }
//
//    }
//    @Test
//    public void testQuery(){
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());
//
//        QGuestbook qGuestbook = QGuestbook.guestbook;
//
//        String keyword = "1";
//
//        BooleanBuilder builder = new BooleanBuilder();
//
//        BooleanExpression expression = qGuestbook.title.contains(keyword);
//
//        builder.and(expression);
//
//        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
//
//        result.stream().forEach(guestbook ->{
//                    System.out.println(guestbook);
//                });
//
//
//    }
//    @Test
//    public void testQuery2(){
//
//        Pageable pageable = PageRequest.of(0,10, Sort.by("id").descending());
//
//        QGuestbook qGuestbook = QGuestbook.guestbook;
//
//        String keyword = "1";
//
//        BooleanBuilder builder = new BooleanBuilder();
//
//        BooleanExpression exTitle = qGuestbook.title.contains(keyword);
//        BooleanExpression exContent = qGuestbook.content.contains(keyword);
//        BooleanExpression exAll = exTitle.or(exContent);
//
//
//        builder.and(exAll);
//        builder.and(qGuestbook.id.gt(0L));
//        Page<Guestbook> result = guestbookRepository.findAll(builder,pageable);
//
//        result.stream().forEach(guestbook ->{
//                    System.out.println(guestbook);
//                });
//
//
//    }

}
