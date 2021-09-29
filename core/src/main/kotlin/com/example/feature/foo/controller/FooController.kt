package com.example.feature.foo.controller

import com.example.feature.foo.service.FooService
import com.example.model.Foo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/foos")
class FooController(private val service: FooService) {

    @GetMapping
    fun listFoo(): List<Foo> = service.listFoo()
}