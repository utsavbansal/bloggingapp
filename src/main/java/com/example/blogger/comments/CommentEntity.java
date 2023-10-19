package com.example.blogger.comments;

import com.example.blogger.articles.ArticleEntity;
import com.example.blogger.common.BaseEntity;
import com.example.blogger.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity(name="comments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class CommentEntity extends BaseEntity {

	private String title;

	@Column(nullable=false)
	@NonNull
	private String body;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private UserEntity author;

	@ManyToOne
	@JoinColumn(name = "artilce_id")
	private ArticleEntity article;

}