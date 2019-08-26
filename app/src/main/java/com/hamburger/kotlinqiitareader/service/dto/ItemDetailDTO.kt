package com.hamburger.kotlinqiitareader.service.dto

import java.io.Serializable

data class ItemDetailDTO(
    val id: String,
    val title: String,
    val body: String
) : Serializable

//https://qiita.com/api/v2/docs#get-apiv2itemsitem_id
//{
//    "rendered_body": "<h1>Example</h1>",
//    "body": "# Example",
//    "coediting": false,
//    "comments_count": 100,
//    "created_at": "2000-01-01T00:00:00+00:00",
//    "group": {
//    "created_at": "2000-01-01T00:00:00+00:00",
//    "id": 1,
//    "name": "Dev",
//    "private": false,
//    "updated_at": "2000-01-01T00:00:00+00:00",
//    "url_name": "dev"
//},
//    "id": "4bd431809afb1bb99e4f",
//    "likes_count": 100,
//    "private": false,
//    "reactions_count": 100,
//    "tags": [
//    {
//        "name": "Ruby",
//        "versions": [
//        "0.0.1"
//        ]
//    }
//    ],
//    "title": "Example title",
//    "updated_at": "2000-01-01T00:00:00+00:00",
//    "url": "https://qiita.com/yaotti/items/4bd431809afb1bb99e4f",
//    "user": {
//    "description": "Hello, world.",
//    "facebook_id": "yaotti",
//    "followees_count": 100,
//    "followers_count": 200,
//    "github_login_name": "yaotti",
//    "id": "yaotti",
//    "items_count": 300,
//    "linkedin_id": "yaotti",
//    "location": "Tokyo, Japan",
//    "name": "Hiroshige Umino",
//    "organization": "Increments Inc",
//    "permanent_id": 1,
//    "profile_image_url": "https://si0.twimg.com/profile_images/2309761038/1ijg13pfs0dg84sk2y0h_normal.jpeg",
//    "team_only": false,
//    "twitter_screen_name": "yaotti",
//    "website_url": "http://yaotti.hatenablog.com"
//},
//    "page_views_count": 100
//}