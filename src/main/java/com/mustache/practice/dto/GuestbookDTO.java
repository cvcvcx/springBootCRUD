package com.mustache.practice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestbookDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;
    //mustache에서 format을 지원하지 않기 때문에 필요한 변수들
    //실제로 2022/10/12 이런식으로 위의 날짜가 변환이 되는데, 그건 Builder패턴이 사용되고있는
    //GuestbookService 의 entityToDto 부분에서 이루어진다.
    private String formattedRegDate, formattedModDate;

}
