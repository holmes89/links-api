package com.jholmestech.linksapi

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Link(@Id val id:String?, val url:String, val title:String?, val icon:String?, val created: LocalDateTime?)