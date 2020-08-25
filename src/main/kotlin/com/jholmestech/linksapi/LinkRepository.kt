package com.jholmestech.linksapi

import org.springframework.data.mongodb.repository.MongoRepository

interface LinkRepository:MongoRepository<Link,String>