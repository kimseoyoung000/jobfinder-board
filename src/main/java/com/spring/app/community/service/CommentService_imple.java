// CommentService 구현체
package com.spring.app.community.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.spring.app.community.domain.CommentDTO;
import com.spring.app.community.model.CommentDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService_imple implements CommentService {

    private final CommentDAO commentDAO;

    @Override
    public List<CommentDTO> getCommentsWithReplies(Long postId) {
        List<CommentDTO> comments = commentDAO.getComments(postId);
        for (CommentDTO comment : comments) {
            List<CommentDTO> replies = commentDAO.getReplies(comment.getCommentId());
            comment.setReplies(replies);
        }
        return comments;
    }

    @Override
    public int insertComment(Long postId, String content, Long parentCommentId, String memberId) {
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("content", content);
        params.put("parentCommentId", parentCommentId);
        params.put("memberId", memberId);
        return commentDAO.insertComment(params);
    }
    
    @Override
    public int editComment(Long commentId, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        params.put("content", content);
        return commentDAO.editComment(params);
    }

    @Override
    public int deleteComment(Long commentId) {
        return commentDAO.deleteComment(commentId);
    }
    
}