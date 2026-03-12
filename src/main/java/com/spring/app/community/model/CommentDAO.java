package com.spring.app.community.model;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.spring.app.community.domain.CommentDTO;

@Mapper
public interface CommentDAO {
    // 댓글 목록 (부모 댓글만)
    List<CommentDTO> getComments(Long postId);
    
    // 대댓글 목록
    List<CommentDTO> getReplies(Long parentCommentId);
    
    // 댓글 등록
    int insertComment(Map<String, Object> params);
    
    // 댓글 수
    int getCommentCount(Long postId);
    
    int editComment(Map<String, Object> params);
    int deleteComment(Long commentId);
}