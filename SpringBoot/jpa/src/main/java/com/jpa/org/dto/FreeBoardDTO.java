package com.jpa.org.dto;

import com.jpa.org.entity.FreeBoard;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class FreeBoardDTO {
    private int idx;
    private String name;
    @Size(min = 3, max = 30, message = "3자 이상 30자 이하")
    private String title;
    @Size(min = 3, max = 30, message = "3자 이상 30자 이하")
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
