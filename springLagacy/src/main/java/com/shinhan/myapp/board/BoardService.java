package com.shinhan.myapp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	BoardRepository repo;
	
	
	public List<BoardDTO> findAll() {
		return repo.findAll();
	}
	
	public BoardDTO findById(Long bno) {
		return repo.findById(bno);
	}
	
	public int insert(BoardDTO board) {
		return repo.insert(board);
	}
	
	public int update(BoardDTO board) {
		return repo.update(board);
	}
	
	public int delete(Long bno) {
		return repo.delete(bno);
	}
}
