import json

from flask import Flask
from flask import jsonify
from flask import request

app = Flask(__name__)

posts = {
    0: {
      "id": 0,
      "upvotes": 1,
      "title": "My cat is the cutest!",
      "link": "https://i.imgur.com/jseZqNK.jpg",
      "username": "alicia98",
      "comments": []
    },
    1: {
        "id": 1,
      "upvotes": 3,
      "title": "Cat loaf",
      "link": "https://i.imgur.com/TJ46wX4.jpg",
      "username": "alicia98",
       "comments": []
    }
}

post_id_counter = 2

@app.route("/")
def hello_world():
    return "Hello world!"

# your routes here
@app.route("/posts/")
def get_posts():
    """Get all posts"""
    res = {"posts" : list(posts.values())}
    return json.dumps(res), 200

@app.route("/posts/", methods=["POST"])
def create_post():
    """Create a post"""
    global post_id_counter
    body = json.loads(request.data)
    title = body.get("title")
    link = body.get("link")
    username = body.get("username")
    post = {
        "id" : post_id_counter,
        "upvotes" : 1,
        "title" : title,
        "link" : link,
        "username" : username,
        "comments": []
    }
    post_id_counter += 1
    posts[post_id_counter] = post
    return json.dumps(post), 201

@app.route("/posts/<int:post_id>/")
def get_post(post_id):
    """Get a specific post by id"""
    post = posts.get(post_id)
    if post is None:
        return json.dumps({"error" : "Posts not found"}), 404
    else:
        return json.dumps(post), 200

@app.route("/posts/<int:post_id>/", methods=["DELETE"])
def delete_post(post_id):
    """Delete a specific post"""
    post = posts.get(post_id)
    if post is None:
        return json.dumps({"error" : "Post not found!"}), 404
    del posts[post_id]
    return json.dumps(post), 200

"""Comments"""
commend_id_counter = 1

@app.route("/posts/<int:post_id>/comments/")
def get_comments_by_id(post_id):
    """Get comments for a specific post"""
    post = posts.get(post_id)
    if post is None:
        return json.dumps({"error" : "Post not found!"}), 404
    res = {"comments": posts.get(post_id).get("comments")}
    return json.dumps(res), 200

comment_id_counter = 0
@app.route("/posts/<int:post_id>/comments/", methods=["POST"])
def create_comment(post_id):
    """Post a comment for a specific post"""
    global comment_id_counter
    body = json.loads(request.data)
    # post_id = body.get("post_id")
    text = body.get("text")
    username = body.get("username")
    # get post by id
    post = posts.get(post_id)
    if post is None:
        return json.dumps({"error" : "Post not found!"}), 404
    comments = post.get("comments")
    comment = {
        "id" : commend_id_counter,
        "upvotes": 1,
        "text": text,
        "username": username
    }
    comment_id_counter += 1
    comments.append(comment)
    print(comments)
    return json.dumps(comment), 201

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8000, debug=True)
