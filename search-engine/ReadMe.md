Search Engine
-------------
	
This is a tiny search engine uses 3 leves of cache
	
 1. in memory
 2. historic seach
 3. lucene search	

Technologies
----

 - Java 8
 - Spring Boot 1.4.0
 - JPA 2.1
 - Hibernate 5.0.9
 - Hibernate Search 5.5.4
 - Lucene 5.3.1
 - HSQL 2.3.3

REST Endpoints
--------------
 1. search
 2. save
 3. delete
 4. clear
 5. getall
 6. initialize
 
 
End point usage
---------------

##1.Initialize

Initlialize will save/index a set of predefined pages

###<i>Request</i>

```sh
GET /initialize
```

###<i>Response</i>

```sh
{
  "initialize": "ok",
  "pages": [
    {
      "name": "P1",
      "weight": null,
      "url": "www.p1.com",
      "stats": {
        "id": 1,
        "lastVisit": null,
        "totalVisit": 0
      },
      "tags": [
        "ford",
        "car",
        "review"
      ],
      "subPages": null
    },
    {
      "name": "P2",
      "weight": null,
      "url": "www.p2.com",
      "stats": {
        "id": 2,
        "lastVisit": null,
        "totalVisit": 0
      },
      "tags": [
        "review",
        "car"
      ],
      "subPages": null
    },
    {
      "name": "P3",
      "weight": null,
      "url": "www.p3.com",
      "stats": {
        "id": 3,
        "lastVisit": null,
        "totalVisit": 0
      },
      "tags": [
        "review",
        "ford"
      ],
      "subPages": null
    },
    {
      "name": "P4",
      "weight": null,
      "url": "www.p4.com",
      "stats": {
        "id": 4,
        "lastVisit": null,
        "totalVisit": 0
      },
      "tags": [
        "toyota",
        "car"
      ],
      "subPages": null
    },
    {
      "name": "P5",
      "weight": null,
      "url": "www.p5.com",
      "stats": {
        "id": 5,
        "lastVisit": null,
        "totalVisit": 0
      },
      "tags": [
        "honda",
        "car"
      ],
      "subPages": null
    },
    {
      "name": "P6",
      "weight": null,
      "url": "www.p6.com",
      "stats": {
        "id": 6,
        "lastVisit": null,
        "totalVisit": 0
      },
      "tags": [
        "car"
      ],
      "subPages": null
    }
  ]
}
```