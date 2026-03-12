// CommentService 인터페이스
package com.spring.app.community.service;

import java.util.List;
import com.spring.app.community.domain.CommentDTO;

public interface CommentService {
    List<CommentDTO> getCommentsWithReplies(Long postId);
    int insertComment(Long postId, String content, Long parentCommentId, String memberId);
    
    int editComment(Long commentId, String content);
    int deleteComment(Long commentId);
}