package com.spring.app.community.service;

import java.util.List;

import com.spring.app.community.domain.BoardDTO;
import com.spring.app.community.domain.CommunityPostDTO;
import com.spring.app.community.domain.ReportReasonDTO;

public interface CommunityService {
    List<BoardDTO> getBoards();
    
    List<CommunityPostDTO> getPosts(Long boardId, int page, String sort, String search);
    
    List<CommunityPostDTO> getHotPosts();
    
    List<CommunityPostDTO> getRecentHotPosts();  
    
    int getTotalCount(Long boardId, String search);
    
    int getMyPostCount(String memberId);  
    
    int getMyCommentCount(String memberId);
    
    BoardDTO getBoardById(Long boardId);
    int writePost(Long boardId, String title, String content, String memberId);
	
 // CommunityService 인터페이스에 추가
    CommunityPostDTO getPostById(Long postId);
    int increaseViewCount(Long postId);
    CommunityPostDTO getPrevPost(Long postId);
    CommunityPostDTO getNextPost(Long postId);

    List<ReportReasonDTO> getReportReasons();
	
    int editPost(Long postId, String title, String content);
    int deletePost(Long postId);
    int insertReport(int targetType, Long targetId, Long reasonId, String reportContent, String memberId);
    
    List<CommunityPostDTO> getPosts(Long boardId, int page, String sort, String search, String type, String memberId);
    int getTotalCount(Long boardId, String search, String type, String memberId);
}


