package com.spring.app.community.model;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;  // ← 이거 추가
import com.spring.app.community.domain.BoardDTO;
import com.spring.app.community.domain.CommunityPostDTO;
import com.spring.app.community.domain.ReportReasonDTO;

@Mapper
public interface CommunityDAO {
    List<BoardDTO> getBoards();
    List<CommunityPostDTO> getPosts(Map<String, Object> params);
    List<CommunityPostDTO> getHotPosts();
    List<CommunityPostDTO> getRecentHotPosts();  
    int getTotalCount(Map<String, Object> params);
    
    int getMyPostCount(@Param("memberId") String memberId);   
    int getMyCommentCount(@Param("memberId") String memberId); 
    
    int writePost(Map<String, Object> params);
    BoardDTO getBoardById(Long boardId);   
    CommunityPostDTO getPostById(Long postId);
    int increaseViewCount(Long postId);
    CommunityPostDTO getPrevPost(Long postId);
    CommunityPostDTO getNextPost(Long postId);
    List<ReportReasonDTO> getReportReasons();
    int editPost(Map<String, Object> params);
    int deletePost(Long postId);
    int insertReport(Map<String, Object> params);
}