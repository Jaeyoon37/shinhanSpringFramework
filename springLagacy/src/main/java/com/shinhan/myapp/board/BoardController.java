package com.shinhan.myapp.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.shinhan.myapp.vo.MemberDTO;

import lombok.extern.slf4j.Slf4j;
import net.starykids.util.UploadFileUtils;

@Slf4j
@RequestMapping("/board")
@Controller
public class BoardController {

	@Autowired
	BoardService bService;
	
	@GetMapping("/list.do")
	public String list(Model model) {
		model.addAttribute("boardlist", bService.findAll());
		return "board/boardList";
	}
	
	@GetMapping("/insert.do")
	public String insertGet() {
		return "board/boardInsert";
	}
	
	@PostMapping("/insert.do")
	public String insertPost(MultipartHttpServletRequest multipart, HttpSession session) {
		MemberDTO member = (MemberDTO) session.getAttribute("loginMember");
		if(member == null) {
			member = MemberDTO.builder().member_id("guest").build();
		}
		String writer = member.getMember_id();
		// 파일 이름 얻어내기
		String uploadPath = session.getServletContext().getRealPath("./resources/upload");
		MultipartFile multipartFile = multipart.getFile("pic");
		String fileName = multipartFile.getOriginalFilename(); //이미지 이름
		String newfileName = "";
		String ymdPath = UploadFileUtils.calcPath(uploadPath);
		log.info(ymdPath);
		try {
			newfileName = UploadFileUtils.fileUpload(uploadPath, fileName, multipartFile.getBytes(), ymdPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "redirect:insert.do";
		}
		HttpServletRequest request = (HttpServletRequest)multipart;
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDTO board = BoardDTO.builder().title(title).content(content).build();
		board.setWriter(writer);
		board.setPic(ymdPath +File.separator+ newfileName);
		log.info(board.toString());
		bService.insert(board);
		return "redirect:list.do";
	}
	
	@GetMapping("/detail.do")
	public String detailGet(Long bno, Model model) {
		model.addAttribute("board", bService.findById(bno));
		return "board/boardDetail";
	}
	
	@PutMapping(value = "/update.do", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody BoardDTO board) {
		int result = bService.update(board);
		return result>0?"수정완료":"수정실패";
	}
	
	@PostMapping("/update.do")
	public String updatePost(BoardDTO board) {
		bService.update(board);
		return "redirect:list.do";
	}
	
	@GetMapping("/delete.do")
	public String delete(Long bno) {
		bService.delete(bno);
		return "redirect:list.do";
	}
	
}
