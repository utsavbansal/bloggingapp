package com.example.blogger.articles;

import java.util.Date;
import java.util.List;

import com.example.blogger.common.BaseEntity;
import com.example.blogger.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name="articles")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleEntity extends BaseEntity {


	@Column(nullable=false)
	@NonNull
	private String heading;

	@Column(nullable=false)
	@NonNull
	private String subheading;


	@Column(nullable=false)
	@NonNull
	private String slug;

	@Column(nullable=false)
	@NonNull
	private String content;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private UserEntity author;
	//private List<String> tags;
}