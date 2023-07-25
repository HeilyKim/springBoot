package com.jpa.org.controller;

import com.jpa.org.dto.FreeBoardDTO;
import com.jpa.org.entity.FreeBoard;
import com.jpa.org.repository.FreeBoardRepository;
import com.jpa.org.service.FreeBoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/FreeBoard")
public class FreeBoardController {

    @Autowired
    FreeBoardService freeBoardService;
    @Autowired
    FreeBoardRepository freeBoardRepository;

    @GetMapping("Delete")
    public @ResponseBody String delete(FreeBoardDTO freeBoardDTO) { //@ResponseBody->html을 찾는것이 아니라 delete 반환값을 view의
        freeBoardRepository.deleteById(freeBoardDTO.getIdx());              // ajax result로 넘겨줌
        return "success";
    }

    @GetMapping("View")
    public String view(@ModelAttribute @Valid FreeBoardDTO freeBoardDTO, BindingResult bindingResult, Model model) {
        System.out.println("idx=" + freeBoardDTO.getIdx());
        FreeBoardDTO dto = freeBoardService.getRow(freeBoardDTO);
        model.addAttribute("freeBoardDTO", dto);
        return "freeboard/view";
    }

    @GetMapping("WriteForm")
    public String writeForm(@ModelAttribute @Valid FreeBoardDTO freeBoardDTO, BindingResult bindingResult, Model model) {
        return "freeboard/writeform";
    }

    @GetMapping("UpdateForm")
    public String UpdateForm(@ModelAttribute @Valid FreeBoardDTO freeBoardDTO, BindingResult bindingResult, Model model) {
        FreeBoardDTO dto = freeBoardService.getRow(freeBoardDTO);
        model.addAttribute("freeBoardDTO", dto);
        return "freeboard/updateform";
    }

    @PostMapping("WriteForm")
    public String pwwriteForm(@ModelAttribute @Valid FreeBoardDTO freeBoardDTO
            , BindingResult bindingResult, Model model
            , HttpServletRequest request
            , HttpSession session
            , Authentication authentication) {
        System.out.println("title: " + request.getParameter("title"));
        System.out.println("username: " + session.getAttribute("username"));
        System.out.println("authentication:" + authentication);
        System.out.println("authentication:" + authentication.getPrincipal());
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            System.out.println("여기되나");
            System.out.println(user.getUsername());
            freeBoardDTO.setName(user.getUsername());
        }
        if (bindingResult.hasErrors()) {
            System.out.println("사이즈에 문제있음");
            return "freeboard/writeform";
        } else {
            System.out.println(freeBoardDTO);
            boolean result = freeBoardService.insert(freeBoardDTO);
            if (result)
                return "redirect:/FreeBoard";
        }
        return "freeboard/writeform";
    }

    @PostMapping("UpdateForm")
    public String pUpdateForm(@ModelAttribute @Valid FreeBoardDTO freeBoardDTO
            , BindingResult bindingResult
            , Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("사이즈에 문제있음");
            model.addAttribute("freeBoardDTO", freeBoardDTO);
            return "freeboard/updateform";
        } else {
            System.out.println(freeBoardDTO);
            boolean result = freeBoardService.insert(freeBoardDTO);
            if (result)
                return "redirect:/FreeBoard";
        }
        return "freeboard/updateform";
    }

    @GetMapping("")
    public String index(Model model, @PageableDefault(
            size = 5, sort = "idx"
            , direction = Sort.Direction.DESC, page = 0) Pageable pageable
            , @RequestParam(required = false, defaultValue = "0") int page
            , @RequestParam(required = false, defaultValue = "") String searchText) {
        Page<FreeBoard> pagelist = freeBoardService.list(searchText, searchText, pageable);
        List<FreeBoard> dblist = new ArrayList<>();
        //list에 담긴 freeboard를 freeboarddto로 변경해서 다시 list에 담음
        List<FreeBoardDTO> dtoList = new ArrayList<>();
        for (FreeBoard fb : pagelist) {
            FreeBoardDTO dto = FreeBoardDTO.of(fb);
            dtoList.add(dto);
        }
        model.addAttribute("curPage", page + 1);
        model.addAttribute("totalElements", pagelist.getTotalElements());
        model.addAttribute("totalPages", pagelist.getTotalPages());
        model.addAttribute("list", pagelist);
        return "freeboard/index";
    }
}
