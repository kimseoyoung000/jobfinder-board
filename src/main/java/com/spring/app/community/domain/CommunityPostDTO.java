package com.spring.app.community.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommunityPostDTO {

    private Long postId;

    private String title;

    private String memberId;

    private Long boardId;
    
    private String boardTitle;

    private int viewCount;

    private int commentCount;
 
    private String content;

    private String postStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    private int isHidden;

}