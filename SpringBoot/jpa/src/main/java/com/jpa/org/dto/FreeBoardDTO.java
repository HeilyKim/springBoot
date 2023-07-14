package com.jpa.org.dto;

import com.jpa.org.entity.FreeBoard;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
public class FreeBoardDTO {
    private int idx;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private static ModelMapper modelMapper = new ModelMapper();

    public FreeBoard createFreeBoard() {
        return modelMapper.map(this, FreeBoard.class); //=builder()
    }

    public static FreeBoardDTO of(FreeBoard freeBoard) {
        return modelMapper.map(freeBoard, FreeBoardDTO.class);
    }
}
