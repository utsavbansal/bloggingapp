# Blogging App

## Features
    - Users can signup to create accounts
        - Users have username, email, bio (text) , avatar (img url)
    - Users can login to their accounts
    - Users can write blog articles
        _ Blog articles should have a heading , subheading, tags and content body
        - Assume reasonable length for heading, subheading, mo limit on body
        - Body content will be html
        - Tags are an array of Strings
     - Users can comment on Blog articles
        -Comments will have a tittle and a body
        -Title is optional, but body is required
    _ Users can like blog articles
    - Users can follow other users
    - Users can see list of all other users
        - Users can see profile of individual users
        - Users can search for profiles by username (or part)
    - There should be a feed of all articles (reverse chronological order)
        - Ability to filter blogs by tags
        - Ability to filter blogs written by a specific user
        - Ability to sort articles by \[date(default), likes\]
    - There should be a feed of all articles of authors a user is following

## Future Scope
- Add a Image upload service so that users \<img\> tags can be embedded in blog articles
- Add support for pagination when listing articles and users.

## Technical Details

### Entities


#### User
'''
json
{
"username":"johnDoe0001",
"email":"john@mail.com",
"bio":"I am a software Developer",
"avatar":"https://avatars.dicebear.com/v2/johndoe0001.png"
}
'''
> NOTE : 'password' has to be hashed and stored too

#### Article

#### Comment
