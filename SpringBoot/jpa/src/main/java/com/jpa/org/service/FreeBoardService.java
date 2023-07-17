package com.jpa.org.service;

import com.jpa.org.dto.FreeBoardDTO;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBoardService {
    @Autowired
    FreeBoardRepository freeBoardRepository;

    public List<FreeBoardDTO> list(Pageable pageable) {
        //db에가서 select해서 내용을 가지고와서 list에 담음
        //Page<->List
        Page<FreeBoard> pagelist = freeBoardRepository.findAll(pageable); //최신으로 sorting
        List<FreeBoard> dblist = new ArrayList<>();
        //list에 담긴 freeboard를 freeboarddto로 변경해서 다시 list에 담음

        List<FreeBoardDTO> dtoList = new ArrayList<>();
        for (FreeBoard fb : pagelist) {
            FreeBoardDTO dto = FreeBoardDTO.of(fb);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean insert(FreeBoardDTO dto) { //@Valid 검사
        FreeBoard freeBoard = dto.createFreeBoard();
        freeBoardRepository.save(freeBoard);
        return true;
    }
}
