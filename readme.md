# Blogging App

## Features
### Required
    - Users can signup to create accounts
        - Users have username, email, bio (text) , avatar (img url)
    - Users can login to their accounts
    - Users can write blog articles
        _ Blog articles should have a heading , subheading, tags and content body
        - Assume reasonable length for heading, subheading, mo limit on body
        - Body content will be html
        - Tags are an array of Strings
        - Users can delete articles they have created
        - Users can update articles they have created
        - Users can delete comments they have created
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
'''json
{
"id":12,
"username":"johnDoe0001",
"email":"john@mail.com",
"bio":"I am a software Developer",
"avatar":"https://avatars.dicebear.com/v2/johndoe0001.png",
"createdAt":"2020-01-01T00:00:00:0007"
}
'''
> NOTE : 'password' has to be hashed and stored too

#### Article
'''json 
{
"id":12,
"heading":"My first blog post",
"slug":"my-first-blog-post",
"subheading":"This is my first blog post",
"tags":["blog","first"],
"content":"<p> This is my first blog post</p>",
"authordId":12,
"createdAt":"2020-01-01T00:00:00:0007"
}
'''
#### Comment
'''json
{
"id":17,
"title":"My first comment",
"body":"This is my first comment",
"articleId":124,
"authordId":42,
"createdAt":"2020-01-01T00:00:00:0007"
}
'''
#### Join Tables

##### Follows
'''json
{
"id":133,
"followerId":12,
"followeeId":42,
"createdAt":"2020-01-01T00:00:00:0007"
}
'''

##### Likes
'''json
{
"id":524,
"likerId":12,
"articleId":142,
"createdAt":"2020-01-01T00:00:00:0007"
}
'''
### Schema Diagram

![BloggerApp drawio](https://github.com/utsavbansal/bloggingapp/assets/16816167/00cc1ac4-af54-4713-a087-ce54796355c9)




### API Endpoints

#### Users

##### 'POST /users'
create a new user (signup)

##### 'POST /users/login'
login to an existing user (login)

##### 'GET /users' (Pagination)
list all users

available query parameters :
- '?sort=date' or '?sort=followers'
- '?username=something' filter by username (i.e. username contains 'something')
- '?follower=Kakarot' (LoggedIn)  users whom 'Kakarot' follows
- '?following=Kakarot' (LoggedIn)  find all 'Kakarot' followers

##### 'POST /users/login'
login to an existing user (login)

##### 'GET /users/{userid}'  (LoggedIn)
get user profile by user id

##### 'GET /users/@{username}' (LoggedIn)
get user profile by username

##### 'PUT /users/@{username}/follow' (LoggedIn)
follow a user
##### 'DELETE /users/@{username}/follow' (LoggedIn) (USER)
unfollow a user

#### Articles

##### 'POST /articles' (LoggedIn)
create a new article

##### 'GET /articles/{articles-slug}' (LoggedId)
get article by slug

##### 'PATCH /articles/{articles-slug}' (LoggedId) (User)
update article by slug

##### 'DELETE /articles/{articles-slug}' (LoggedId) (User)
delete article by slug

##### 'PUT /articles/{articles-slug}/like' (LoggedId) (User})
like article by slug

##### 'DELETE /articles/{articles-slug}/like' (LoggedId) (User)
unlike article by slug

##### 'GET /articles/'  (Pagination)
get all articles

available query parameters:
- '?following=true' (default: false) (LoggedIn): get articles of users that you are following
- '?sort=date' (default) or '?sort=likes' : sort by date or likes
- '?tags=startups,tech' : filter articles by tags
- '?author=username' : filter articles by author
- '?title=something' : search articles by title (i.e. title includes 'something')

<details>
    <summary>Response</summary>
    '''json
    [
        {
        "id":12,
        "heading":"My first blog post",
        "slug":"my-first-blog-post",
        "subheading":"This is my first blog post",
        "tags":["blog","first"],
        "author":{
            "username":"kakarot",
            "avatar": "https://avatars.dicebear.com/v2/johndoe0001.png"
        },
        "createdAt":"2020-01-01T00:00:00:0007"
        },
        {
        "id":42,
        "heading":"My first blog post",
        "slug":"another-nice-article",
        "subheading":"This is another nice article",
        "tags":["article","first"],
        "author":{
            "username": "kakarot",
            "avatar": "https://avatars.dicebear.com/v2/johndoe0001.png"
        },
        "createdAt":"2020-01-01T00:00:00:0007"
        }
    ]
    '''
</details>



#### Comments

##### 'POST /articles/{article-slug}/comments'  (LoggedIn)
create a new comment on a given article

##### 'POST /articles/{article-slug}/comments'  (Pagination)
get all comments on a given article

##### 'DELETE /articles/{article-slug}/comments/{comment-id}'  (LoggedIn) (User)
delete comment on a given article