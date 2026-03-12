package com.spring.app.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.app.community.domain.BoardDTO;
import com.spring.app.community.domain.CommunityPostDTO;
import com.spring.app.community.domain.ReportReasonDTO;
import com.spring.app.community.model.CommunityDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService_imple implements CommunityService {

    private final CommunityDAO communityDAO;

    @Override
    public List<BoardDTO> getBoards() {
        return communityDAO.getBoards();
    }
    
    @Override
    public BoardDTO getBoardById(Long boardId) {
        return communityDAO.getBoardById(boardId);  // null → 이렇게 수정
    }

    @Override
    public List<CommunityPostDTO> getPosts(Long boardId, int page, String sort, String search) {

    	Map<String,Object> param = new HashMap<>();

    	param.put("boardId", boardId);
    	param.put("search", search);
    	param.put("sort", sort);

    	int start = (page-1)*10 + 1;
    	int end = page*10;

    	param.put("start", start);
    	param.put("end", end);
    	
    	return communityDAO.getPosts(param);
    }

    @Override
    public List<CommunityPostDTO> getHotPosts() {
        return communityDAO.getHotPosts();
    }

    @Override
    public int getTotalCount(Long boardId, String search) {
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", boardId);
        params.put("search", search);
        return communityDAO.getTotalCount(params);
    }
    
    @Override
    public List<CommunityPostDTO> getRecentHotPosts() {
        return communityDAO.getRecentHotPosts();
    }

    @Override
    public int getMyPostCount(String memberId) {
        return communityDAO.getMyPostCount(memberId);
    }

    @Override
    public int getMyCommentCount(String memberId) {
        return communityDAO.getMyCommentCount(memberId);
    }
	
    @Override
    public int writePost(Long boardId, String title, String content, String memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", boardId);
        params.put("title", title);
        params.put("content", content);
        params.put("memberId", memberId);
        return communityDAO.writePost(params);
    }
    
 // CommunityService_imple에 추가
    @Override
    public CommunityPostDTO getPostById(Long postId) {
        return communityDAO.getPostById(postId);
    }

    @Override
    public int increaseViewCount(Long postId) {
        return communityDAO.increaseViewCount(postId);
    }

    @Override
    public CommunityPostDTO getPrevPost(Long postId) {
        return communityDAO.getPrevPost(postId);
    }

    @Override
    public CommunityPostDTO getNextPost(Long postId) {
        return communityDAO.getNextPost(postId);
    }
    
    @Override
    public List<ReportReasonDTO> getReportReasons() {
        return communityDAO.getReportReasons();
    }
    
    @Override
    public int editPost(Long postId, String title, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("title", title);
        params.put("content", content);
        return communityDAO.editPost(params);
    }

    @Override
    public int deletePost(Long postId) {
        return communityDAO.deletePost(postId);
    }

    @Override
    public int insertReport(int targetType, Long targetId, Long reasonId, String reportContent, String memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("targetType", targetType);
        params.put("targetId", targetId);
        params.put("reasonId", reasonId);
        params.put("reportContent", reportContent);
        params.put("memberId", memberId);
        return communityDAO.insertReport(params);
    }

    @Override
    public List<CommunityPostDTO> getPosts(Long boardId, int page, String sort, String search, String type, String memberId) {
        Map<String, Object> param = new HashMap<>();
        param.put("boardId", boardId);
        param.put("search", search);
        param.put("sort", sort);
        param.put("type", type);
        param.put("memberId", memberId);
        int start = (page - 1) * 10 + 1;
        int end = page * 10;
        param.put("start", start);
        param.put("end", end);
        return communityDAO.getPosts(param);
    }

    @Override
    public int getTotalCount(Long boardId, String search, String type, String memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", boardId);
        params.put("search", search);
        params.put("type", type);
        params.put("memberId", memberId);
        return communityDAO.getTotalCount(params);
    }
	
	
}
