package com.jholmestech.linksapi

import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("api/links")
@CrossOrigin(origins = ["*"])
class LinkController(private val service : LinkService) {
    @GetMapping
    fun getAll(): List<Link> = service.findAll()

    @GetMapping("{id}") fun getById(@PathVariable id:String): Optional<Link> = service.findById(id)

    @PostMapping
    fun insert(@RequestBody idea: Link): Link = service.create(idea)

    @DeleteMapping("{id}")
    fun delete(@PathVariable id:String) = service.delete(id)
}