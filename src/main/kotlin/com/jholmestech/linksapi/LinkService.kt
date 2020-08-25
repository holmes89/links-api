package com.jholmestech.linksapi

import org.jsoup.Jsoup
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.URL
import java.time.LocalDateTime
import java.util.*



interface LinkService {
    fun findById(id: String) : Optional<Link>
    fun findAll() : List<Link>
    fun create(link: Link) : Link
    fun delete(id: String)
}

@Service
class LinkServiceImp(private val repository: LinkRepository) : LinkService {

    val logger: Logger = LoggerFactory.getLogger(LinkServiceImp::class.java)

    override fun findById(id: String): Optional<Link> {
        return repository.findById(id)
    }

    override fun findAll(): List<Link> {
        return repository.findAll()
    }

    @Throws(Exception::class)
    override fun create(link: Link): Link {
        if(link.url.isEmpty()) {
            throw Exception("url cannot be empty")
        }
        logger.info("fetching content for site: ", link.url)
        val doc = Jsoup.connect(link.url).get()
        val title = doc.title()
        val iconName = doc.head().select("link[href~=.*\\.png]").first().attr("href")
        val iconUrl = try {
            val url = URL(link.url)
            val baseUrl: String = url.protocol.toString() + "://" + url.host
            if (iconName.contains(baseUrl)) iconName else baseUrl + iconName
        } catch (e: Exception) {
            logger.error("unable to get icon: ", link.url)
            ""
        }
        val l  = Link(id = null, url = link.url, title = title, icon = iconUrl, created = LocalDateTime.now())
        return repository.insert(l)
    }

    override fun delete(id: String) {
        repository.deleteById(id)
    }

}