package com.mustache.practice.service;

import com.mustache.practice.dto.GuestbookDTO;
import com.mustache.practice.dto.PageRequestDTO;
import com.mustache.practice.dto.PageResultDTO;
import com.mustache.practice.entity.Guestbook;
import com.mustache.practice.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Log4j2
@RequiredArgsConstructor
@Service
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository guestbookRepository;

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("Dto....................");
        log.info(dto);

        Guestbook entity = dtoToEntity(dto);

        log.info(entity);
        guestbookRepository.save(entity);
        return entity.getId();
    }

    @Override
    public GuestbookDTO read(Long id) {

        Optional<Guestbook> result = guestbookRepository.findById(id);

        return result.isPresent() ? entityToDTO(result.get()) : null;
    }

    @Override
    public void remove(Long id) {
        guestbookRepository.deleteById(id);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = guestbookRepository.findById(dto.getId());

        if (result.isPresent()) {
            Guestbook entity = result.get();
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());

            guestbookRepository.save(entity);
        }

    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        //GuestbookRepository 에서 해온것 처럼 Pageable 을 구하고
        //그 결과를 Page<T> 로 받는다.
        //이후 PageResultDTO 로 반환을 해야 하기 때문에
        //함수를 전달한다. entityToDTO 는 GuestbookService 에서 만들어놓은 default 메서드임
        Pageable pageable = requestDTO.getPageable(Sort.by("id")
                                                       .descending());
        Page<Guestbook> result = guestbookRepository.findAll(pageable);

        Function<Guestbook, GuestbookDTO> fn = (entity) -> entityToDTO(entity);
        return new PageResultDTO<>(result, fn);

    }
}