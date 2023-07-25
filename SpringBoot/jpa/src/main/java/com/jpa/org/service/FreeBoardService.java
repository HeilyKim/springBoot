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

    public Page<FreeBoard> list(String searchText, String SearchText, Pageable pageable) {
        //db에가서 select해서 내용을 가지고와서 list에 담음
        //Page<->List
        Page<FreeBoard> pagelist = freeBoardRepository
                .findByTitleContainingOrContentContaining(searchText,SearchText,pageable);
        return pagelist;
    }

    public boolean insert(FreeBoardDTO dto) { //@Valid 검사
        FreeBoard freeBoard = freeBoardRepository.findById(dto.getIdx()).orElse(new FreeBoard());
        freeBoard.setContent(dto.getContent());
        freeBoard.setName(dto.getName());
        freeBoard.setTitle(dto.getTitle());
        freeBoardRepository.save(freeBoard);
        return true;
    }

    public FreeBoardDTO getRow(FreeBoardDTO freeBoardDTO) {
        FreeBoard freeBoard = freeBoardRepository.findById(freeBoardDTO.getIdx())
                .orElse(new FreeBoard());
        return FreeBoardDTO.of(freeBoard);

    }
}
