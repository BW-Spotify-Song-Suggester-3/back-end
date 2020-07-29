# back-end

Format: {
        "userid": 4,
        "username": "admin",
        "primaryemail": "admin@lambdaschool.local",
        "useremails": [
            {
                "useremailid": 5,
                "useremail": "admin@email.local"
            },
            {
                "useremailid": 6,
                "useremail": "admin@mymail.local"
            }
        ],
        "roles": [
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            }
        ],
        "songs": [
            {
                "songid": 10,
                "name": "Jammin",
                "artist": "Bob Marley",
                "spotifyid": "982",
                "album": "Exodus",
                "previewurl": "www.previewurl.com",
                "albumcover": "www.albumcoverurl.com",
                "releasedate": "June 3, 1977"
            }
        ]
    }



ENDPOINTS


(Get Token/Login)

GET

https://tjs-songsuggest.herokuapp.com/login


(List all users)

GET

https://tjs-songsuggest.herokuapp.com/users/users


(get user by id)

GET

https://tjs-songsuggest.herokuapp.com/users/user/{userId}


(get user by name)

GET

https://tjs-songsuggest.herokuapp.com/users/user/name/{userName}


(Add new user)

POST

https://tjs-songsuggest.herokuapp.com/createnewuser


(Update full user)

PUT

https://tjs-songsuggest.herokuapp.com/users/user/{userid}


(Update user)

PUT

https://tjs-songsuggest.herokuapp.com/users/user/{userid}


(Delete user)

DELETE

https://tjs-songsuggest.herokuapp.com/users/user/{userid}




(List all songs)

GET

https://tjs-songsuggest.herokuapp.com/songs/user/{userid}


(Get song by id)

GET

https://tjs-songsuggest.herokuapp.com/songs/song/{songId}


(Add new song)

POST

https://tjs-songsuggest.herokuapp.com/songs/create/user/{userid}/song


(Update song)

PUT

https://tjs-songsuggest.herokuapp.com/songs/update/song/{songid}


(Delete song)

DELETE

https://tjs-songsuggest.herokuapp.com/songs/delete/song/{songid}





(Logout user)

GET

https://tjs-songsuggest.herokuapp.com/logout



(TEST TOKEN)
token name= admin token

grant type= password credentials

username= admin

password= password

clientid= lambda-client

clientsecret= lambda-secret



