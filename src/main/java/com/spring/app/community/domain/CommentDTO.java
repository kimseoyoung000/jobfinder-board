package com.spring.app.community.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class CommentDTO {
    private Long commentId;
    private Long postId;
    private String memberId;
    private String content;
    private Long parentCommentId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private int isHidden;
    
    // 대댓글 목록
    private List<CommentDTO> replies;
}