package com.example.blogger.users;

import com.example.blogger.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name="users")
public class UserEntity extends BaseEntity  {

	@Column(nullable=false)
	@NonNull
	private String email;

	@Column(nullable=false)
	@NonNull
	private String username;

	private String bio;
	private String avatar;
}