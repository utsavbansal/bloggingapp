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

	@Column(nullable=false,unique = true)
	@NonNull
	private String email;

	@Column(nullable=false,unique = true)
	@NonNull
	private String username;

	@Column(nullable = false)
	@NonNull
	private String password;

	private String bio;
	private String avatar;
}